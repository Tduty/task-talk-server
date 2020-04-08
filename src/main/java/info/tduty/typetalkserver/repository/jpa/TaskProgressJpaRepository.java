package info.tduty.typetalkserver.repository.jpa;

import info.tduty.typetalkserver.data.entity.TaskProgressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskProgressJpaRepository extends CrudRepository<TaskProgressEntity, String> {
}
