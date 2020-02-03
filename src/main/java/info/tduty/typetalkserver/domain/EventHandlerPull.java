package info.tduty.typetalkserver.domain;

import info.tduty.typetalkserver.data.event.EventPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class EventHandlerPull {

    private Map<EventPayload.Type, EventHandler> map;

    @Autowired
    public EventHandlerPull(MessageNewHandler messageNewHandler,
                            LessonHandler lessonHandler,
                            TypingHandler typingHandler) {
        map = new HashMap<>();
        map.put(EventPayload.Type.MESSAGE_NEW, messageNewHandler);
        map.put(EventPayload.Type.LESSON, lessonHandler);
        map.put(EventPayload.Type.TYPING, typingHandler);
    }

    public EventHandler getHandler(String type) {
        return map.get(EventPayload.Type.to(type));
    }
}
