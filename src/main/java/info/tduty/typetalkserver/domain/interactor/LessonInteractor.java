package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.dto.LessonDTO;
import info.tduty.typetalkserver.data.entity.LessonEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.domain.mapper.LessonMapper;
import info.tduty.typetalkserver.repository.wrapper.LessonWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LessonInteractor {

    private UserWrapper userWrapper;
    private LessonWrapper lessonWrapper;
    private LessonMapper lessonMapper;

    @Autowired
    public LessonInteractor(UserWrapper userWrapper, LessonWrapper lessonWrapper, LessonMapper lessonMapper) {
        this.userWrapper = userWrapper;
        this.lessonWrapper = lessonWrapper;
        this.lessonMapper = lessonMapper;
    }

    public List<LessonDTO> getLessons(User user) {
        UserEntity userEntity = userWrapper.get(user.getId()).orElseThrow(IllegalArgumentException::new);
        List<LessonEntity> lessons = lessonWrapper.getByClassId(userEntity.getClassEntity().getId());
        return lessons.stream().map(lesson -> lessonMapper.dbToDto(userEntity, lesson)).collect(Collectors.toList());
    }

    public LessonDTO getLessonById(User user, String lessonId) {
        UserEntity userEntity = userWrapper.get(user.getId()).orElseThrow(IllegalArgumentException::new);
        LessonEntity lesson = lessonWrapper.getByLessonId(lessonId).orElseThrow(IllegalArgumentException::new);
        return lessonMapper.dbToDto(userEntity, lesson);
    }
}
