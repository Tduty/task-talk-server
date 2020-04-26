package info.tduty.typetalkserver.domain.handler.pull;

import info.tduty.typetalkserver.data.event.EventPayload;
import info.tduty.typetalkserver.domain.handler.*;
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
                            TypingHandler typingHandler,
                            CorrectionHandler correctionHandler,
                            TaskHandler taskHandler,
                            LessonProgressHandler lessonProgressHandler) {
        map = new HashMap<>();
        map.put(EventPayload.Type.MESSAGE_NEW, messageNewHandler);
        map.put(EventPayload.Type.LESSON, lessonHandler);
        map.put(EventPayload.Type.TYPING, typingHandler);
        map.put(EventPayload.Type.CORRECTION, correctionHandler);
        map.put(EventPayload.Type.TASK, taskHandler);
        map.put(EventPayload.Type.LESSON_PROGRESS, lessonProgressHandler);
    }

    public EventHandler getHandler(String type) {
        return map.get(EventPayload.Type.to(type));
    }
}
