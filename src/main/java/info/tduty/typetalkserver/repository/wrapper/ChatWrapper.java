package info.tduty.typetalkserver.repository.wrapper;

import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.ClassEntity;
import info.tduty.typetalkserver.repository.jpa.ChatJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ChatWrapper {

    private ChatJpaRepository chatJpaRepository;

    @Autowired
    public ChatWrapper(ChatJpaRepository chatJpaRepository) {
        this.chatJpaRepository = chatJpaRepository;
    }

    public Optional<ChatEntity> get(String id) {
        return chatJpaRepository.findById(id);
    }

    public ChatEntity add(ChatEntity chatEntity) {
        return chatJpaRepository.save(chatEntity);
    }

    public List<ChatEntity> getByTaskId(String lessonId, String taskId) {
        return chatJpaRepository.findByTaskId(lessonId, taskId);
    }
}
