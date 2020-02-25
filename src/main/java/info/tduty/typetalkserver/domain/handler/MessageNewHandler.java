package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.MessageEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.data.event.payload.MessageNewPayload;
import info.tduty.typetalkserver.domain.mapper.MessageMapper;
import info.tduty.typetalkserver.repository.wrapper.ChatWrapper;
import info.tduty.typetalkserver.repository.wrapper.MessageWrapper;
import info.tduty.typetalkserver.service.ws.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class MessageNewHandler implements EventHandler<MessageNewPayload> {

    private MessageWrapper messageWrapper;
    private MessageMapper messageMapper;
    private ChatWrapper chatWrapper;
    private EventSender eventSender;

    @Autowired
    public MessageNewHandler(MessageWrapper messageWrapper, MessageMapper messageMapper,
                             ChatWrapper chatWrapper, EventSender eventSender) {
        this.messageWrapper = messageWrapper;
        this.messageMapper = messageMapper;
        this.chatWrapper = chatWrapper;
        this.eventSender = eventSender;
    }

    @Override
    public void handle(User user, MessageNewPayload payload) {
        MessageEntity message = messageMapper.payloadToDB(payload);
        messageWrapper.add(message);
        Optional<ChatEntity> chat = chatWrapper.get(payload.getChatId());
        if (chat.isPresent()) {
            List<UserEntity> users = chat.get().getChatMembers();
            List<String> userIds = users.stream().map(UserEntity::getId).collect(Collectors.toList());
            eventSender.send(userIds, messageMapper.dbToEvent(message));
        }
    }
}
