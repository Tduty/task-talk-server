package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.dto.MessageDTO;
import info.tduty.typetalkserver.data.entity.MessageEntity;
import info.tduty.typetalkserver.domain.mapper.MessageMapper;
import info.tduty.typetalkserver.repository.wrapper.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HistoryInteractor {

    private MessageWrapper messageWrapper;
    private MessageMapper messageMapper;

    @Autowired
    public HistoryInteractor(MessageWrapper messageWrapper, MessageMapper messageMapper) {
        this.messageWrapper = messageWrapper;
        this.messageMapper = messageMapper;
    }

    public List<MessageDTO> get(String chatId) {
        List<MessageEntity> messages = messageWrapper.getByChatId(chatId);
        return messages.stream().map(messageMapper::dbToDTO).collect(Collectors.toList());
    }
}
