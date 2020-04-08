package info.tduty.typetalkserver.domain.mapper;

import info.tduty.typetalkserver.data.dto.TaskDTO;
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
                taskProgress.getStatus()
        );
    }
}
