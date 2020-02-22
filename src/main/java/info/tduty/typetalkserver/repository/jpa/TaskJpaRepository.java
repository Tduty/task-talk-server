package info.tduty.typetalkserver.repository.jpa;

import info.tduty.typetalkserver.data.entity.TaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskJpaRepository extends CrudRepository<TaskEntity, String> {
}
