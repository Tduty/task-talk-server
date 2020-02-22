package info.tduty.typetalkserver.repository.wrapper;

import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.MessageEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.repository.jpa.MessageJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MessageWrapper {

    private MessageJpaRepository messageJpaRepository;

    @Autowired
    public MessageWrapper(MessageJpaRepository messageJpaRepository) {
        this.messageJpaRepository = messageJpaRepository;
    }

    public Optional<MessageEntity> get(String id) {
        return messageJpaRepository.findById(id);
    }

    public List<MessageEntity> getByChatId(String chatId) {
        return messageJpaRepository.findByChatId(chatId);
    }

    public MessageEntity add(MessageEntity message) {
        return messageJpaRepository.save(message);
    }
}
