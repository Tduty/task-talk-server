package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.ClassEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.repository.wrapper.ChatWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class ChatInteractor {

    private UserWrapper userWrapper;
    private ChatWrapper chatWrapper;

    @Autowired
    public ChatInteractor(UserWrapper userWrapper, ChatWrapper chatWrapper) {
        this.userWrapper = userWrapper;
        this.chatWrapper = chatWrapper;
    }

    public ChatEntity createOneToOne(String firstUserId, String secondUserId) {
        UserEntity firstUser = userWrapper.get(firstUserId).orElse(null);
        UserEntity secondUser = userWrapper.get(secondUserId).orElse(null);
        List<UserEntity> users = new ArrayList<>();
        users.add(firstUser);
        users.add(secondUser);
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setTitle("TEST " + UUID.randomUUID());
        chatEntity.setChatMembers(users);
        return chatEntity;
    }

    public ChatEntity createGroupChat(List<String> userIds, ClassEntity classEntity) {
        List<UserEntity> users = new ArrayList<>();
        for (String userId : userIds) {
            UserEntity user = userWrapper.get(userId).orElse(null);
            users.add(user);
        }
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setTitle("TEST " + UUID.randomUUID());
        chatEntity.setChatMembers(users);
        return chatWrapper.add(chatEntity);
    }
}
