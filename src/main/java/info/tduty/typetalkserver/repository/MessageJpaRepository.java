package info.tduty.typetalkserver.repository;

import info.tduty.typetalkserver.data.entity.MessageEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageJpaRepository extends CrudRepository<MessageEntity, Long> {
}
