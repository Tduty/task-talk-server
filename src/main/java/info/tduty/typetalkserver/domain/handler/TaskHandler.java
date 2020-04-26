package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.entity.LessonProgressEntity;
import info.tduty.typetalkserver.data.entity.TaskProgressEntity;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.event.EventPayload;
import info.tduty.typetalkserver.data.event.payload.LessonProgressPayload;
import info.tduty.typetalkserver.data.event.payload.TaskPayload;
import info.tduty.typetalkserver.repository.wrapper.LessonWrapper;
import info.tduty.typetalkserver.service.ws.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                    task.setStatus(getTaskStatus(payload.getCompleted()));
                }
            });

            lessonWrapper.saveTaskProgress(new ArrayList<>(entity.getTasksProgress()));

            entity.setStatus(getLessonState(entity));
            lessonWrapper.save(entity);

            eventSender.send(user.getId(), dbToLTaskUpdateEvent(payload));
            eventSender.send(user.getId(), dbToLessonUpdateEvent(entity));
        });
    }

    private Integer getTaskStatus(Boolean isCompleted) {
        final int NO_COMPLETED = 0;
        final int COMPLETED = 1;
        return isCompleted ? COMPLETED : NO_COMPLETED;
    }

    private Integer getLessonState(LessonProgressEntity entity) {
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

    private Event dbToLessonUpdateEvent(LessonProgressEntity lesson) {
        LessonProgressPayload payload = new LessonProgressPayload(
                lesson.getLesson().getId(),
                lesson.getStatus()
        );
        return new Event(EventPayload.Type.LESSON_PROGRESS.getString(), payload);
    }

    private Event dbToLTaskUpdateEvent(TaskPayload taskPayload) {
        TaskPayload event = new TaskPayload(taskPayload.getLessonId(), taskPayload.getTaskId(), taskPayload.getCompleted());
        return new Event(EventPayload.Type.TASK.getString(), event);
    }
}
