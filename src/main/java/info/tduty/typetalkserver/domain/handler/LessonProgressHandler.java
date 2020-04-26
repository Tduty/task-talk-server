package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.entity.LessonProgressEntity;
import info.tduty.typetalkserver.data.entity.TaskProgressEntity;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.event.EventPayload;
import info.tduty.typetalkserver.data.event.payload.LessonPayload;
import info.tduty.typetalkserver.data.event.payload.LessonProgressPayload;
import info.tduty.typetalkserver.domain.interactor.LessonInteractor;
import info.tduty.typetalkserver.repository.wrapper.LessonWrapper;
import info.tduty.typetalkserver.service.ws.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class LessonProgressHandler implements EventHandler<LessonProgressPayload> {

    private EventSender eventSender;
    private LessonWrapper lessonWrapper;

    @Autowired
    public LessonProgressHandler(EventSender eventSender, LessonWrapper lessonWrapper) {
        this.eventSender = eventSender;
        this.lessonWrapper = lessonWrapper;
    }

    @Override
    public void handle(User user, LessonProgressPayload payload) {
        Optional<LessonProgressEntity> lessonEntity = lessonWrapper.getByLessonId(user.getName(), payload.getLessonId());

        lessonEntity.ifPresent( lesson -> {
            lesson.setStatus(getNewState(lesson));
            lessonWrapper.save(lesson);
            eventSender.send(user.getId(), dbToEvent(lesson));
        });

    }

    private Integer getNewState(LessonProgressEntity entity) {
        boolean isProgress = entity.getTasksProgress().stream().anyMatch(task -> task.getStatus() == 1);

        final int NO_EXECUTING = 0;
        final int IN_PROGRESS = 1;
        final int COMPLETED = 2;

        List<TaskProgressEntity> noOptionalTasks = entity.getTasksProgress()
                .stream()
                .filter(taskProgress -> !taskProgress.getTask().isOptional()).collect(Collectors.toList());

        for (TaskProgressEntity task : noOptionalTasks) {
            if (task.getStatus() != IN_PROGRESS) {
                return isProgress ? IN_PROGRESS : NO_EXECUTING;
            }
        }

        return COMPLETED;
    }

    private Event dbToEvent(LessonProgressEntity lesson) {
        LessonProgressPayload payload = new LessonProgressPayload(
                lesson.getLesson().getId(),
                lesson.getStatus()
        );
        return new Event(EventPayload.Type.LESSON_PROGRESS.getString(), payload);
    }
}
