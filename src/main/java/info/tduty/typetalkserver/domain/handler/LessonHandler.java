package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.entity.LessonProgressEntity;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.event.EventPayload;
import info.tduty.typetalkserver.data.event.payload.LessonPayload;
import info.tduty.typetalkserver.domain.interactor.LessonInteractor;
import info.tduty.typetalkserver.repository.wrapper.LessonWrapper;
import info.tduty.typetalkserver.service.ws.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LessonHandler implements EventHandler<LessonPayload> {

    private EventSender eventSender;
    private LessonWrapper lessonWrapper;
    private LessonInteractor lessonInteractor;

    @Autowired
    public LessonHandler(EventSender eventSender, LessonWrapper lessonWrapper, LessonInteractor lessonInteractor) {
        this.eventSender = eventSender;
        this.lessonWrapper = lessonWrapper;
        this.lessonInteractor = lessonInteractor;
    }

    @Override
    public void handle(User user, LessonPayload payload) {
        lessonInteractor.addLessonToClass(payload.getClassId(), payload.getId());
        List<LessonProgressEntity> lessons = lessonWrapper.getAllByLessonId(payload.getId());
        for (LessonProgressEntity lesson : lessons) {
            eventSender.send(lesson.getExecutor().getId(), dbToEvent(lesson));
        }
    }

    private Event dbToEvent(LessonProgressEntity lesson) {
        LessonPayload payload = new LessonPayload();
        payload.setId(lesson.getLesson().getId());
        return new Event(EventPayload.Type.LESSON.getString(), payload);
    }
}
