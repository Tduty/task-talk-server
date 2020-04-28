package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.MessageEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.event.EventPayload;
import info.tduty.typetalkserver.data.event.payload.CorrectionPayload;
import info.tduty.typetalkserver.repository.wrapper.MessageWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import info.tduty.typetalkserver.service.ws.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CorrectionHandler implements EventHandler<CorrectionPayload> {

    private MessageWrapper messageWrapper;
    private UserWrapper userWrapper;
    private EventSender eventSender;

    @Autowired
    public CorrectionHandler(MessageWrapper messageWrapper, UserWrapper userWrapper, EventSender eventSender) {
        this.messageWrapper = messageWrapper;
        this.userWrapper = userWrapper;
        this.eventSender = eventSender;
    }

    @Override
    @Transactional
    public void handle(User user, CorrectionPayload payload) {
        UserEntity teacher = userWrapper.get(user.getId()).orElse(null);
        if (teacher == null || !teacher.getTeacher()) throw new IllegalArgumentException("not enough rights");
        MessageEntity message = messageWrapper.getBySyncId(payload.getSyncId()).orElse(null);
        if (message == null) throw new IllegalArgumentException("message not found");
        message.setAdditional(payload.getAdditional());
        message.setAdditionalType(payload.getAdditionalType());
        messageWrapper.add(message);
        ChatEntity chat = message.getChat();
        if (chat != null) {
            Set<UserEntity> users = chat.getChatMembers();
            List<String> userIds = users.stream().map(UserEntity::getId).collect(Collectors.toList());
            userIds.add(teacher.getId());
            eventSender.send(userIds, new Event(EventPayload.Type.CORRECTION.getString(), payload));
        }
    }
}
