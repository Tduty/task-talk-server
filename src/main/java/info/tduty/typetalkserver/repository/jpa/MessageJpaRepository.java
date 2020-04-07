package info.tduty.typetalkserver.repository.jpa;

import info.tduty.typetalkserver.data.entity.MessageEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageJpaRepository extends CrudRepository<MessageEntity, String> {

    @Query(value = "SELECT * FROM Message WHERE CHAT_ID = ?1",
            countQuery = "SELECT count(*) FROM Message WHERE CHAT_ID = ?1",
            nativeQuery = true)
    List<MessageEntity> findByChatId(String chatId);

    @Query(value = "SELECT * FROM Message WHERE sync_id = ?1", nativeQuery = true)
    MessageEntity findBySyncId(String syncId);
}
