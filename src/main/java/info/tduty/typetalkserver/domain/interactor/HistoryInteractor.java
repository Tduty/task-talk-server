package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.dto.MessageDTO;
import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.MessageEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.domain.mapper.MessageMapper;
import info.tduty.typetalkserver.repository.wrapper.MessageWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class HistoryInteractor {

    private MessageWrapper messageWrapper;
    private MessageMapper messageMapper;
    private UserWrapper userWrapper;

    @Autowired
    public HistoryInteractor(MessageWrapper messageWrapper, MessageMapper messageMapper, UserWrapper userWrapper) {
        this.messageWrapper = messageWrapper;
        this.messageMapper = messageMapper;
        this.userWrapper = userWrapper;
    }

    public List<MessageDTO> getAll(String username) {
        UserEntity userEntity = userWrapper.getByUsername(username).orElse(null);
        if (userEntity == null) throw new IllegalArgumentException("username is not exist");
        List<MessageEntity> messages = new ArrayList<>();
        for (ChatEntity chat : userEntity.getChats()) {
            messages.addAll(chat.getMessages());
        }
        return messages.stream().map(messageMapper::dbToDTO).collect(Collectors.toList());
    }

    public List<MessageDTO> get(String chatId) {
        List<MessageEntity> messages = messageWrapper.getByChatId(chatId);
        return messages.stream().map(messageMapper::dbToDTO).collect(Collectors.toList());
    }
}
