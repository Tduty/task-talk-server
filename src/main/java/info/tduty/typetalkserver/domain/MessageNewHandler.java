package info.tduty.typetalkserver.domain;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.event.payload.MessageNewPayload;
import org.springframework.stereotype.Component;


@Component
public class MessageNewHandler implements EventHandler<MessageNewPayload> {

    @Override
    public void handle(User user, MessageNewPayload payload) {

    }
}
