package info.tduty.typetalkserver.repository.wrapper;

import info.tduty.typetalkserver.data.entity.ClassEntity;
import info.tduty.typetalkserver.data.entity.LessonEntity;
import info.tduty.typetalkserver.repository.jpa.LessonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LessonWrapper {

    private LessonJpaRepository lessonJpaRepository;

    @Autowired
    public LessonWrapper(LessonJpaRepository lessonJpaRepository) {
        this.lessonJpaRepository = lessonJpaRepository;
    }

    public List<LessonEntity> getByClassId(String classId) {
        Iterable<LessonEntity> itr = lessonJpaRepository.findAll();
        return getCollectionFromIteralbe(itr).stream()
                .filter(lesson -> lesson.getClasses().stream()
                        .map(ClassEntity::getId).collect(Collectors.toList()).contains(classId))
                .collect(Collectors.toList());

    }

    public Optional<LessonEntity> getByLessonId(String lessonId) {
        return lessonJpaRepository.findById(lessonId);
    }

    private static List<LessonEntity> getCollectionFromIteralbe(Iterable<LessonEntity> itr) {
        List<LessonEntity> cltn = new ArrayList<LessonEntity>();
        for (LessonEntity t : itr)
            cltn.add(t);
        return cltn;
    }
}
