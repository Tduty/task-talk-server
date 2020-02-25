package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.event.EventPayload;
import info.tduty.typetalkserver.data.event.payload.TypingPayload;
import info.tduty.typetalkserver.repository.wrapper.ChatWrapper;
import info.tduty.typetalkserver.service.ws.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TypingHandler implements EventHandler<TypingPayload> {

    private ChatWrapper chatWrapper;
    private EventSender eventSender;

    @Autowired
    public TypingHandler(ChatWrapper chatWrapper, EventSender eventSender) {
        this.chatWrapper = chatWrapper;
        this.eventSender = eventSender;
    }

    @Override
    public void handle(User user, TypingPayload payload) {
        Optional<ChatEntity> chat = chatWrapper.get(payload.getChatId());
        if (chat.isPresent()) {
            List<UserEntity> users = chat.get().getChatMembers();
            List<String> userIds = users.stream()
                    .map(UserEntity::getId)
                    .filter(id -> id.equals(user.getId()))
                    .collect(Collectors.toList());
            eventSender.send(userIds, new Event(
                    EventPayload.Type.TYPING.getString(),
                    new TypingPayload(user.getId(), payload.getChatId(), payload.getType()))
            );
        }
    }
}
