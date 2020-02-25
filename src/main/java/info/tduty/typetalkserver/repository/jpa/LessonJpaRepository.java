package info.tduty.typetalkserver.repository.jpa;

import info.tduty.typetalkserver.data.entity.LessonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonJpaRepository extends CrudRepository<LessonEntity, String> {
}
