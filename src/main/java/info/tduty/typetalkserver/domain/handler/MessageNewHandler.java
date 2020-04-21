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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
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
    @Transactional
    public void handle(User user, MessageNewPayload payload) {
        MessageEntity message = messageMapper.payloadToDB(payload);
        MessageEntity messageNew = messageWrapper.add(message);
        ChatEntity chat = messageNew.getChat();
        if (chat != null) {
            Set<UserEntity> users = chat.getChatMembers();
            List<String> userIds = users.stream().map(UserEntity::getId).collect(Collectors.toList());
            eventSender.send(userIds, messageMapper.dbToEvent(message));
        }
    }
}
