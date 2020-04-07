package info.tduty.typetalkserver.domain.mapper;

import info.tduty.typetalkserver.data.dto.LessonDTO;
import info.tduty.typetalkserver.data.entity.LessonEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class LessonMapper {

    public LessonDTO dbToDto(UserEntity user, LessonEntity lesson) {
        int status = 0;
        if (lesson.getCompleted().contains(user)) {
            status = 2;
        } else if (lesson.getExecute().contains(user)) {
            status = 1;
        }
        return new LessonDTO(
                lesson.getId(),
                lesson.getTitle(),
                lesson.getAvatar(),
                status,
                lesson.getDescription(),
                Collections.emptyList(), //TODO
                Collections.emptyList() //TODO
        );
    }
}
