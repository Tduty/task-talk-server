package info.tduty.typetalkserver.repository;

import info.tduty.typetalkserver.data.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepository extends CrudRepository<TaskEntity, Long> {
}
