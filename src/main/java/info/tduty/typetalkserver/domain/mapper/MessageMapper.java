package info.tduty.typetalkserver.domain.mapper;

import info.tduty.typetalkserver.data.dto.MessageDTO;
import info.tduty.typetalkserver.data.entity.MessageEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.event.EventPayload;
import info.tduty.typetalkserver.data.event.payload.MessageNewPayload;
import info.tduty.typetalkserver.repository.wrapper.ChatWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Date;

@Controller
public class MessageMapper {

    private ChatWrapper chatWrapper;
    private UserWrapper userWrapper;

    @Autowired
    public MessageMapper(ChatWrapper chatWrapper, UserWrapper userWrapper) {
        this.chatWrapper = chatWrapper;
        this.userWrapper = userWrapper;
    }

    public MessageEntity payloadToDB(MessageNewPayload payload) {
        MessageEntity message = new MessageEntity();
        message.setSyncId(payload.getId());
        message.setContent(payload.getBody());
        message.setChat(chatWrapper.get(payload.getChatId()).orElse(null));
        message.setSender(userWrapper.get(payload.getSenderId()).orElse(null));
        message.setTime(new Date().getTime());
        return message;
    }

    public Event dbToEvent(MessageEntity message) {
        MessageNewPayload payload = new MessageNewPayload(
                message.getSyncId(),
                message.getChat().getId(),
                message.getSender().getId(),
                getSenderType(message.getSender()),
                message.getContent(),
                message.getTime(),
                false
        );
        return new Event(EventPayload.Type.MESSAGE_NEW.getString(), payload);
    }

    public MessageDTO dbToDTO(MessageEntity message) {
        MessageDTO dto = new MessageDTO(
                message.getSyncId(),
                message.getChat().getId(),
                message.getSender().getId(),
                getSenderType(message.getSender()),
                message.getContent(),
                new Date().getTime(),
                false
        );
        return dto;
    }

    private String getSenderType(UserEntity user) {
        String type = user.getSex();
        if (user.getTeacher()) type = "teacher";
        return type;
    }
}
