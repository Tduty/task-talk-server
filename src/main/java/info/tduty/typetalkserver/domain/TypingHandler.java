package info.tduty.typetalkserver.domain;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.event.payload.TypingPayload;
import org.springframework.stereotype.Component;

@Component
public class TypingHandler implements EventHandler<TypingPayload> {

    @Override
    public void handle(User user, TypingPayload payload) {

    }
}
