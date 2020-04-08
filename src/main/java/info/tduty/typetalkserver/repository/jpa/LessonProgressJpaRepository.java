package info.tduty.typetalkserver.repository.jpa;

import info.tduty.typetalkserver.data.entity.LessonProgressEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonProgressJpaRepository extends CrudRepository<LessonProgressEntity, String> {

    @Query(value = "SELECT * FROM LessonProgress WHERE LESSON_ID = ?1",
            countQuery = "SELECT count(*) FROM LessonProgress WHERE LESSON_ID = ?1",
            nativeQuery = true)
    List<LessonProgressEntity> findByLessonId(String lessonId);
}
