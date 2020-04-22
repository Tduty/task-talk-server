package info.tduty.typetalkserver.domain.mapper;

import info.tduty.typetalkserver.data.dto.TaskDTO;
import info.tduty.typetalkserver.data.dto.TeacherTaskDTO;
import info.tduty.typetalkserver.data.entity.TaskEntity;
import info.tduty.typetalkserver.data.entity.TaskProgressEntity;
import org.springframework.stereotype.Component;


@Component
public class TaskMapper {

    public TaskDTO dbToDto(TaskProgressEntity taskProgress) {
        if (taskProgress.getTask()== null) return null;
        return new TaskDTO(
                taskProgress.getTask().getId(),
                taskProgress.getTask().getAvatar(),
                taskProgress.getTask().getTitle(),
                taskProgress.getTask().getType(),
                taskProgress.getTask().getPosition(),
                taskProgress.getStatus(),
                taskProgress.getTask().isOptional(),
                taskProgress.getTask().getContent()
        );
    }

    public TeacherTaskDTO dbToTeacherDto(TaskEntity task, int studentCount, Integer completedCount) {
        return new TeacherTaskDTO(
                task.getId(),
                task.getLesson().getId(),
                task.getAvatar(),
                task.getTitle(),
                task.getType(),
                task.getPosition(),
                completedCount != null ? completedCount : 0,
                studentCount
        );
    }
}
