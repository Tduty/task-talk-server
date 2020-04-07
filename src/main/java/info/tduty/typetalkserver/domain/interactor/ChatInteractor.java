package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.dto.ChatDTO;
import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.ClassEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.repository.wrapper.ChatWrapper;
import info.tduty.typetalkserver.repository.wrapper.ClassWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ChatInteractor {

    private UserWrapper userWrapper;
    private ChatWrapper chatWrapper;
    private ClassWrapper classWrapper;

    @Autowired
    public ChatInteractor(UserWrapper userWrapper, ChatWrapper chatWrapper, ClassWrapper classWrapper) {
        this.userWrapper = userWrapper;
        this.chatWrapper = chatWrapper;
        this.classWrapper = classWrapper;
    }

    public ChatEntity createOneToOne(String firstUserId, String secondUserId) {
        UserEntity firstUser = userWrapper.get(firstUserId).orElse(null);
        UserEntity secondUser = userWrapper.get(secondUserId).orElse(null);
        Set<UserEntity> users = new HashSet<>();
        users.add(firstUser);
        users.add(secondUser);
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setTitle("TEST " + UUID.randomUUID());
        chatEntity.setChatMembers(users);
        return chatEntity;
    }

    public ChatEntity createGroupChat(List<String> userIds, ClassEntity classEntity) {
        Set<UserEntity> users = new HashSet<>();
        for (String userId : userIds) {
            UserEntity user = userWrapper.get(userId).orElse(null);
            users.add(user);
        }
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setTitle("TEST " + UUID.randomUUID());
        chatEntity.setChatMembers(users);
        return chatWrapper.add(chatEntity);
    }

    public ChatDTO getTeacherChat(String username) {
        UserEntity user = userWrapper.getByUsername(username).orElseThrow(IllegalArgumentException::new);
        if (user.getChats() == null) throw new IllegalArgumentException("chats is null");
        for (ChatEntity chatEntity : user.getChats()) {
            if (chatEntity.getChatMembers().size() > 2) continue;
            for (UserEntity userEntity : chatEntity.getChatMembers()) {
                if (userEntity.getTeacher())return mapToDTO(chatEntity);
            }
        }
        throw new IllegalArgumentException("chat not search");
    }

    public ChatDTO getClassChat(String username) {
        UserEntity user = userWrapper.getByUsername(username).orElseThrow(IllegalArgumentException::new);
        if (user.getChats() == null) throw new IllegalArgumentException("chats is null");
        for (ChatEntity chatEntity : user.getChats()) {
            if (chatEntity.getChatMembers().size() > 2) {
                return mapToDTO(chatEntity);
            }
        }
        throw new IllegalArgumentException("class chat not search");
    }

    public List<ChatDTO> getChats(String username) {
        UserEntity user = userWrapper.getByUsername(username).orElseThrow(IllegalArgumentException::new);
        if (user.getChats() == null) throw new IllegalArgumentException("chats is null");
        List<ChatDTO> chats = new ArrayList<>();
        for (ChatEntity chatEntity : user.getChats()) {
            chats.add(mapToDTO(chatEntity));
        }
        return chats;
    }


    private ChatDTO mapToDTO(ChatEntity chatEntity) {
        return new ChatDTO(
                chatEntity.getId(),
                chatEntity.getTitle(),
                chatEntity.getAvatar(),
                chatEntity.getType(),
                chatEntity.getDescription()
        );
    }
}
