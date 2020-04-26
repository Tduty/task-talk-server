package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.entity.LessonProgressEntity;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.event.EventPayload;
import info.tduty.typetalkserver.data.event.payload.TaskPayload;
import info.tduty.typetalkserver.repository.wrapper.LessonWrapper;
import info.tduty.typetalkserver.service.ws.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TaskHandler implements EventHandler<TaskPayload> {

    private EventSender eventSender;
    private LessonWrapper lessonWrapper;

    @Autowired
    public TaskHandler(EventSender eventSender, LessonWrapper lessonWrapper) {
        this.eventSender = eventSender;
        this.lessonWrapper = lessonWrapper;
    }

    @Override
    public void handle(User user, TaskPayload payload) {
        Optional<LessonProgressEntity> lessonEntity = lessonWrapper.getByLessonId(user.getName(), payload.getLessonId());

        lessonEntity.ifPresent(entity -> {
            entity.getTasksProgress().forEach( task -> {
                if (task.getTask().getId().equals(payload.getTaskId())) {
                    task.setStatus(getStatus(payload.getCompleted()));
                }
            });
            lessonWrapper.saveTaskProgress(new ArrayList<>(entity.getTasksProgress()));
            TaskPayload taskPayload = new TaskPayload(payload.getLessonId(), payload.getTaskId(), payload.getCompleted());
            eventSender.send(user.getId(), new Event(EventPayload.Type.TASK.getString(), taskPayload));
        });
    }

    private Integer getStatus(Boolean isCompleted) {
        return isCompleted ? 1 : 0;
    }
}
