package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.dto.ClassCreatedDTO;
import info.tduty.typetalkserver.data.dto.CreateClassDTO;
import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.ClassEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.domain.mapper.ClassMapper;
import info.tduty.typetalkserver.domain.mapper.UserMapper;
import info.tduty.typetalkserver.repository.wrapper.ChatWrapper;
import info.tduty.typetalkserver.repository.wrapper.ClassWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ClassInteractor {

    private UserWrapper userWrapper;
    private ChatWrapper chatWrapper;
    private UserMapper userMapper;
    private ClassMapper classMapper;
    private ChatInteractor chatInteractor;
    private ClassWrapper classWrapper;

    @Autowired
    public ClassInteractor(UserWrapper userWrapper, ChatWrapper chatWrapper, UserMapper userMapper,
                           ClassMapper classMapper, ChatInteractor chatInteractor, ClassWrapper classWrapper) {
        this.userWrapper = userWrapper;
        this.chatWrapper = chatWrapper;
        this.userMapper = userMapper;
        this.classMapper = classMapper;
        this.chatInteractor = chatInteractor;
        this.classWrapper = classWrapper;
    }

    public ClassCreatedDTO create(CreateClassDTO createClass) {
        List<UserEntity> users = new ArrayList<>();
        for (CreateClassDTO.UserDTO user : createClass.getUsers()) {
            Optional<UserEntity> userEntity = userWrapper.get(user.getId());
            if (userEntity.isPresent()) {
                users.add(userEntity.get());
            } else {
                users.add(userWrapper.add(userMapper.dtoToDB(user)));
            }
        }
        ClassEntity classEntity = classMapper.dtoToDB(createClass, users);
        List<String> userIds = users.stream().map(UserEntity::getId).collect(Collectors.toList());
        ClassEntity classDB = classWrapper.add(classEntity);
        chatInteractor.createGroupChat(userIds, classDB);
        return classMapper.dbToCreatedDTO(classEntity);
    }
}
