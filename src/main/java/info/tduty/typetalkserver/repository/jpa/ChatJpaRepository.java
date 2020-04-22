package info.tduty.typetalkserver.repository.jpa;

import info.tduty.typetalkserver.data.entity.ChatEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatJpaRepository extends CrudRepository<ChatEntity, String> {

    @Query(value = "SELECT * FROM Chat WHERE lesson_id = ?1 AND task_id = ?2",
            countQuery = "SELECT count(*) FROM Message WHERE lesson_id = ?1 AND task_id = ?2",
            nativeQuery = true)
    List<ChatEntity> findByTaskId(String lessonId, String taskId);
}
