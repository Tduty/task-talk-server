package info.tduty.typetalkserver.repository.wrapper;

import info.tduty.typetalkserver.data.entity.*;
import info.tduty.typetalkserver.repository.jpa.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.beans.Transient;
import java.util.*;

@Repository
public class LessonWrapper {

    private LessonJpaRepository lessonJpaRepository;
    private LessonProgressJpaRepository lessonProgressJpaRepository;
    private TaskJpaRepository taskJpaRepository;
    private TaskProgressJpaRepository taskProgressJpaRepository;
    private ClassJpaRepository classJpaRepository;
    private UserJpaRepository userJpaRepository;

    @Autowired
    public LessonWrapper(LessonJpaRepository lessonJpaRepository,
                         LessonProgressJpaRepository lessonProgressJpaRepository,
                         TaskJpaRepository taskJpaRepository,
                         TaskProgressJpaRepository taskProgressJpaRepository,
                         ClassJpaRepository classJpaRepository,
                         UserJpaRepository userJpaRepository) {
        this.lessonJpaRepository = lessonJpaRepository;
        this.lessonProgressJpaRepository = lessonProgressJpaRepository;
        this.taskJpaRepository = taskJpaRepository;
        this.taskProgressJpaRepository = taskProgressJpaRepository;
        this.classJpaRepository = classJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    public LessonEntity save(LessonEntity lesson) {
        return lessonJpaRepository.save(lesson);
    }

    public LessonProgressEntity save(LessonProgressEntity lesson) {
        return lessonProgressJpaRepository.save(lesson);
    }

    public Iterable<TaskEntity> saveTasks(List<TaskEntity> tasks) {
        return taskJpaRepository.saveAll(tasks);
    }


    public Iterable<LessonProgressEntity> saveProgress(List<LessonProgressEntity> lessons) {
        return lessonProgressJpaRepository.saveAll(lessons);
    }

    public void saveTaskProgress(List<TaskProgressEntity> tasks) {
        taskProgressJpaRepository.saveAll(tasks);
    }

    public Iterable<LessonEntity> getAll() {
        return lessonJpaRepository.findAll();
    }

    public List<LessonEntity> getByClassId(String classId) {
        ClassEntity classEntity = classJpaRepository.findById(classId).orElse(null);
        if (classEntity == null) return Collections.emptyList();
        else return new ArrayList<>(classEntity.getLessons());

    }

    public List<LessonProgressEntity> getAllByLessonId(String lessonId) {
        return lessonProgressJpaRepository.findByLessonId(lessonId);
    }

    public Optional<LessonEntity> getByLessonId(String lessonId) {
        return lessonJpaRepository.findById(lessonId);
    }

    public Set<LessonProgressEntity> getAll(String username) {
        UserEntity userEntity = userJpaRepository.findByUsername(username);
        if (userEntity == null) return Collections.emptySet();
        if (userEntity.getLessons() != null) return userEntity.getLessons();
        else return Collections.emptySet();
    }

    @Transactional
    public Optional<LessonProgressEntity> getByLessonId(String username, String lessonId) {
        UserEntity userEntity = userJpaRepository.findByUsername(username);
        if (userEntity == null) return Optional.empty();
        LessonProgressEntity lessonProgressEntity = null;
        for (LessonProgressEntity lessonProgress : userEntity.getLessons()) {
            if (lessonId.equals(lessonProgress.getLesson().getId())) lessonProgressEntity = lessonProgress;
        }
        if (lessonProgressEntity == null) return Optional.empty();
        else return Optional.of(lessonProgressEntity);
    }

    public Set<TaskProgressEntity> getTasksByLessonId(String username, String lessonId) {
        UserEntity userEntity = userJpaRepository.findByUsername(username);
        if (userEntity == null) return Collections.emptySet();
        LessonProgressEntity lessonProgressEntity = null;
        for (LessonProgressEntity lessonProgress : userEntity.getLessons()) {
            if (lessonId.equals(lessonProgress.getLesson().getId())) lessonProgressEntity = lessonProgress;
        }
        if (lessonProgressEntity == null) return Collections.emptySet();
        else return lessonProgressEntity.getTasksProgress();
    }
}
