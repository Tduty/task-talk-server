package info.tduty.typetalkserver.repository.wrapper;

import info.tduty.typetalkserver.data.entity.DictionaryEntity;
import info.tduty.typetalkserver.data.entity.LessonEntity;
import info.tduty.typetalkserver.data.entity.LessonProgressEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.repository.jpa.DictionaryJpaRepository;
import info.tduty.typetalkserver.repository.jpa.LessonJpaRepository;
import info.tduty.typetalkserver.repository.jpa.LessonProgressJpaRepository;
import info.tduty.typetalkserver.repository.jpa.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Component
public class DictionaryWrapper {

    private UserJpaRepository userJpaRepository;
    private LessonJpaRepository lessonJpaRepository;
    private DictionaryJpaRepository dictionaryJpaRepository;
    private LessonProgressJpaRepository lessonProgressJpaRepository;

    @Autowired
    public DictionaryWrapper(UserJpaRepository userJpaRepository,
                             LessonJpaRepository lessonJpaRepository,
                             DictionaryJpaRepository dictionaryJpaRepository,
                             LessonProgressJpaRepository lessonProgressJpaRepository) {
        this.userJpaRepository = userJpaRepository;
        this.lessonJpaRepository = lessonJpaRepository;
        this.dictionaryJpaRepository = dictionaryJpaRepository;
        this.lessonProgressJpaRepository = lessonProgressJpaRepository;
    }

    public Iterable<DictionaryEntity> save(List<DictionaryEntity> dictionaries) {
        return dictionaryJpaRepository.saveAll(dictionaries);
    }

    public List<DictionaryEntity> getAllByUsername(String username) {
        UserEntity user = userJpaRepository.findByUsername(username);
        if (user == null) return Collections.emptyList();
        Set<LessonProgressEntity> lessons = user.getLessons();
        if (lessons == null) return Collections.emptyList();
        List<DictionaryEntity> dictionaries = new ArrayList<>();
        for (LessonProgressEntity lesson : lessons) {
            if (lesson.getLesson() == null) continue;
            if (lesson.getLesson().getDictionaries() == null) continue;
            dictionaries.addAll(lesson.getLesson().getDictionaries());
        }
        return dictionaries;
    }

    public Set<DictionaryEntity> getByLessonId(String lessonId) {
        LessonEntity lesson = lessonJpaRepository.findById(lessonId).orElse(null);
        if (lesson == null) return Collections.emptySet();
        if (lesson.getDictionaries() == null) return Collections.emptySet();
        return lesson.getDictionaries();
    }
}
