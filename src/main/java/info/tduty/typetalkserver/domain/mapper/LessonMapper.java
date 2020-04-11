package info.tduty.typetalkserver.domain.mapper;

import info.tduty.typetalkserver.data.dto.LessonDTO;
import info.tduty.typetalkserver.data.entity.LessonEntity;
import info.tduty.typetalkserver.data.entity.LessonProgressEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class LessonMapper {

    private TaskMapper taskMapper;

    @Autowired
    public LessonMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public LessonDTO dbToDto(LessonProgressEntity lessonProgress) {
        if (lessonProgress.getLesson() == null) return null;
        return new LessonDTO(
                lessonProgress.getLesson().getId(),
                lessonProgress.getLesson().getTitle(),
                lessonProgress.getLesson().getAvatar(),
                lessonProgress.getStatus(),
                lessonProgress.getLesson().getDescription(),
                Collections.emptyList(),
                lessonProgress.getTasksProgress()
                        .stream()
                        .map(task -> taskMapper.dbToDto(task))
                        .collect(Collectors.toList())
        );
    }
}
