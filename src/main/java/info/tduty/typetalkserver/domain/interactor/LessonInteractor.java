package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.dto.LessonDTO;
import info.tduty.typetalkserver.data.entity.*;
import info.tduty.typetalkserver.domain.mapper.LessonMapper;
import info.tduty.typetalkserver.repository.wrapper.ClassWrapper;
import info.tduty.typetalkserver.repository.wrapper.LessonWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class LessonInteractor {

    private ClassWrapper classWrapper;
    private LessonWrapper lessonWrapper;
    private LessonMapper lessonMapper;

    @Autowired
    public LessonInteractor(ClassWrapper classWrapper, LessonWrapper lessonWrapper, LessonMapper lessonMapper) {
        this.classWrapper = classWrapper;
        this.lessonWrapper = lessonWrapper;
        this.lessonMapper = lessonMapper;
    }

    public List<LessonDTO> getLessons(String username) {
        Set<LessonProgressEntity> lessons = lessonWrapper.getAll(username);
        return lessons.stream().map(lesson -> lessonMapper.dbToDto(lesson)).collect(Collectors.toList());
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
        lesson.getClasses().add(classEntity);
        LessonEntity lessonEntity = lessonWrapper.save(lesson);
        generateLessonForAllUsers(lessonEntity, classEntity.getStudents());
    }

    public void generateLessonForAllUsers(LessonEntity lesson, List<UserEntity> users) {
        List<LessonProgressEntity> lessonsProgress = new ArrayList<>();
        for (UserEntity user : users) {
            LessonProgressEntity lessonProgress = new LessonProgressEntity();
            lessonProgress.setLesson(lesson);
            lessonProgress.setExecutor(user);
            lessonProgress.setTasksProgress(generateTasksProgress(lesson, user));
            lessonProgress.setStatus(0);
            lessonsProgress.add(lessonProgress);
        }
        lessonWrapper.saveProgress(lessonsProgress);
    }

    public Set<TaskProgressEntity> generateTasksProgress(LessonEntity lessonEntity, UserEntity user) {
        Set<TaskProgressEntity> tasks = new HashSet<>();
        for (TaskEntity taskEntity : lessonEntity.getTasks()) {
            TaskProgressEntity taskProgress = new TaskProgressEntity();
            taskProgress.setTask(taskEntity);
            taskProgress.setExecutor(user);
            taskProgress.setStatus(0);
            tasks.add(taskProgress);
        }
        return tasks;
    }
}
