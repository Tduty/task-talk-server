package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.dto.CreateDialogDTO;
import info.tduty.typetalkserver.data.dto.LessonDTO;
import info.tduty.typetalkserver.data.dto.TeacherLessonDTO;
import info.tduty.typetalkserver.data.dto.TeacherTaskDTO;
import info.tduty.typetalkserver.data.entity.*;
import info.tduty.typetalkserver.domain.mapper.LessonMapper;
import info.tduty.typetalkserver.domain.mapper.TaskMapper;
import info.tduty.typetalkserver.repository.wrapper.ChatWrapper;
import info.tduty.typetalkserver.repository.wrapper.ClassWrapper;
import info.tduty.typetalkserver.repository.wrapper.LessonWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class LessonInteractor {

    private ClassWrapper classWrapper;
    private LessonWrapper lessonWrapper;
    private LessonMapper lessonMapper;
    private ChatWrapper chatWrapper;
    private UserWrapper userWrapper;
    private TaskMapper taskMapper;

    @Autowired
    public LessonInteractor(ClassWrapper classWrapper,
                            LessonWrapper lessonWrapper,
                            LessonMapper lessonMapper,
                            ChatWrapper chatWrapper,
                            UserWrapper userWrapper,
                            TaskMapper taskMapper) {
        this.classWrapper = classWrapper;
        this.lessonWrapper = lessonWrapper;
        this.lessonMapper = lessonMapper;
        this.chatWrapper = chatWrapper;
        this.userWrapper = userWrapper;
        this.taskMapper = taskMapper;
    }

    public List<LessonDTO> getLessons(String username) {
        Set<LessonProgressEntity> lessons = lessonWrapper.getAll(username);
        return lessons.stream().map(lesson -> lessonMapper.dbToDto(lesson)).collect(Collectors.toList());
    }

    //TODO переписать и переархитектурить
    public List<TeacherLessonDTO> getTeacherLessons(String username, String classId) {
        ClassEntity classEntity = classWrapper.get(classId).orElseThrow(IllegalAccessError::new);
        if (!classEntity.getTeacher().getName().equals(username))
            throw new IllegalArgumentException("Access error you are not a teacher");
        Map<String, Integer> completedLessons = new HashMap<>();
        for (UserEntity student : classEntity.getStudents()) {
            for (LessonProgressEntity lesson : student.getLessons()) {
                if (lesson.getStatus() == 2) { //TODO убрать магическое число
                    String lessonId = lesson.getLesson().getId();
                    Integer count = completedLessons.get(lessonId);
                    completedLessons.put(lessonId, count == null ? 1 : count + 1);
                } else {
                    String lessonId = lesson.getLesson().getId();
                    Integer count = completedLessons.get(lessonId);
                    completedLessons.put(lessonId, count == null ? 0 : count);
                }
            }
        }
        int studentCount = classEntity.getStudents().size();
        List<TeacherLessonDTO> teacherLessonDTOS = new ArrayList<>();
        for (LessonEntity lesson : lessonWrapper.getAll()) {
            teacherLessonDTOS.add(lessonMapper.dbToTeacherDto(lesson, studentCount, completedLessons.get(lesson.getId())));
        }
        return teacherLessonDTOS;
    }

    //TODO переписать и переархитектурить
    public List<TeacherTaskDTO> getTeacherTasks(String username, String lessonId, String classId) {
        ClassEntity classEntity = classWrapper.get(classId).orElseThrow(IllegalAccessError::new);
        if (!classEntity.getTeacher().getName().equals(username))
            throw new IllegalArgumentException("Access error you are not a teacher");
        LessonEntity lesson = lessonWrapper.getByLessonId(lessonId).orElseThrow(IllegalAccessError::new);
        Map<String, Integer> completedTasks = new HashMap<>();
        for (UserEntity student : classEntity.getStudents()) {
            for (TaskProgressEntity task : student.getTasks()) {
                if (task.getLessonProgress().getLesson().getId().equals(lessonId) && task.getStatus() == 1) { //TODO убрать магическое число
                    String taskId = task.getTask().getId();
                    Integer count = completedTasks.get(taskId);
                    completedTasks.put(taskId, count == null ? 1 : count + 1);
                }
            }
        }
        int studentCount = classEntity.getStudents().size();
        return lesson.getTasks().stream()
                .map(task -> taskMapper.dbToTeacherDto(task, studentCount, completedTasks.get(task.getId())))
                .collect(Collectors.toList());
    }

    public LessonDTO getLessonById(String username, String lessonId) {
        LessonProgressEntity lesson = lessonWrapper.getByLessonId(username, lessonId)
                .orElseThrow(IllegalArgumentException::new);
        return lessonMapper.dbToDto(lesson);
    }

    public void addLessonToClass(String classId, String lessonId) {
        ClassEntity classEntity = classWrapper.get(classId).orElse(null);
        if (classEntity == null) return;
        LessonEntity lesson = lessonWrapper.getByLessonId(lessonId).orElse(null);
        if (lesson == null) return;
        if (lesson.getClasses() == null) {
            Set<ClassEntity> classEntities = new HashSet<>();
            classEntities.add(classEntity);
            lesson.setClasses(classEntities);
        } else lesson.getClasses().add(classEntity);
        LessonEntity lessonEntity = lessonWrapper.save(lesson);
        generateLessonForAllUsers(lessonEntity, classEntity.getStudents());
    }

    public void generateLessonForAllUsers(LessonEntity lesson, Set<UserEntity> users) {
        List<LessonProgressEntity> lessonsProgress = new ArrayList<>();
        for (UserEntity user : users) {
            LessonProgressEntity lessonProgress = new LessonProgressEntity();
            lessonProgress.setLesson(lesson);
            lessonProgress.setExecutor(user);
            lessonProgress.setStatus(0);
            lessonsProgress.add(lessonProgress);
        }
        Iterable<LessonProgressEntity> newLesson = lessonWrapper.saveProgress(lessonsProgress);
        for (LessonProgressEntity lessonProgress : newLesson) {
            generateTasksProgress(lessonProgress, lessonProgress.getExecutor());
        }
    }

    public void generateTasksProgress(LessonProgressEntity lessonEntity, UserEntity user) {
        List<TaskProgressEntity> tasks = new ArrayList<>();
        for (TaskEntity taskEntity : lessonEntity.getLesson().getTasks()) {
            TaskProgressEntity taskProgress = new TaskProgressEntity();
            taskProgress.setLessonProgress(lessonEntity);
            taskProgress.setTask(taskEntity);
            taskProgress.setExecutor(user);
            taskProgress.setStatus(0);
            tasks.add(taskProgress);
        }
        lessonWrapper.saveTaskProgress(tasks);
    }

    public void createDialogs(CreateDialogDTO createDialogDTO) {
        List<String> members = createDialogDTO.getMembers();
        Collections.shuffle(members);
        boolean odd = (members.size() % 2) == 1;
        for (int i = 0; i < members.size(); i += 2) {
            List<String> chatMember;
            if (odd && i == members.size() - 3) {
                chatMember = Arrays.asList(members.get(i), members.get(i + 1), members.get(i + 2));
                i += 3;
            } else {
                chatMember = Arrays.asList(members.get(i), members.get(i + 1));
            }
            saveChat(
                    createDialogDTO.getLessonId(),
                    createDialogDTO.getTaskId(),
                    chatMember
            );
        }
    }

    private void saveChat(String lessonId, String taskId, List<String> memberIds) {
        Set<UserEntity> members = getMembers(memberIds);
        ChatEntity chat = new ChatEntity();
        chat.setTitle(getNameForChat(members));
        chat.setType("task");
        chat.setLessonId(lessonId);
        chat.setTaskId(taskId);
        chat.setChatMembers(members);
        chatWrapper.add(chat);
    }

    private String getNameForChat(Set<UserEntity> members) {
        StringJoiner joiner = new StringJoiner(" - ");
        for (UserEntity user : members) {
            joiner.add(user.getName());
        }
        return joiner.toString();
    }

    private Set<UserEntity> getMembers(List<String> members) {
        Set<UserEntity> users = new HashSet<>();
        for (String memberId : members) {
            userWrapper.get(memberId).ifPresent(users::add);
        }
        return users;
    }
}
