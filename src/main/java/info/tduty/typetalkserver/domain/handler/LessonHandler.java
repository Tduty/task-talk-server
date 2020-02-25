package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.event.payload.LessonPayload;
import org.springframework.stereotype.Component;

@Component
public class LessonHandler implements EventHandler<LessonPayload> {

    @Override
    public void handle(User user, LessonPayload payload) {

    }
}
