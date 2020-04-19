package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.dto.ClassCreatedDTO;
import info.tduty.typetalkserver.data.dto.ClassDTO;
import info.tduty.typetalkserver.data.dto.CreateClassDTO;
import info.tduty.typetalkserver.data.dto.StudentDTO;
import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.ClassEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.domain.mapper.ClassMapper;
import info.tduty.typetalkserver.domain.mapper.UserMapper;
import info.tduty.typetalkserver.repository.wrapper.ChatWrapper;
import info.tduty.typetalkserver.repository.wrapper.ClassWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import info.tduty.typetalkserver.service.ws.SessionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Component
public class ClassInteractor {

    private UserWrapper userWrapper;
    private ChatWrapper chatWrapper;
    private UserMapper userMapper;
    private ClassMapper classMapper;
    private ChatInteractor chatInteractor;
    private ClassWrapper classWrapper;
    private SessionRegistry sessionRegistry;

    @Autowired
    public ClassInteractor(UserWrapper userWrapper, ChatWrapper chatWrapper, UserMapper userMapper,
                           ClassMapper classMapper, ChatInteractor chatInteractor, ClassWrapper classWrapper,
                           SessionRegistry sessionRegistry) {
        this.userWrapper = userWrapper;
        this.chatWrapper = chatWrapper;
        this.userMapper = userMapper;
        this.classMapper = classMapper;
        this.chatInteractor = chatInteractor;
        this.classWrapper = classWrapper;
        this.sessionRegistry = sessionRegistry;
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

    public List<ClassDTO> get(String username) {
        try {
            UserEntity user = userWrapper.getByUsername(username).orElseThrow((Supplier<Throwable>) () -> new IllegalArgumentException("Username not exist"));
            if (user.getTeacher()) {
                return user.getClasses().stream().map(classEntity -> classMapper.dbToClassDTO(classEntity)).collect(Collectors.toList());
            } else return Collections.emptyList();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<StudentDTO> get(String username, String classId) {
        try {
            UserEntity teacher = userWrapper.getByUsername(username).orElseThrow((Supplier<Throwable>) () -> new IllegalArgumentException("Username not exist"));
            if (teacher.getTeacher()) {
                ClassEntity classEntity = classWrapper.get(classId).orElseThrow((Supplier<Throwable>) () -> new IllegalArgumentException("class not exist"));
                Set<UserEntity> users = classEntity.getStudents();
                return users.stream().map(user -> {
                    ChatEntity chat = findChatWithTeacher(user, teacher);
                    return userMapper.dbToStudentDTO(
                            user,
                            chat != null ? chat.getId() : "null",
                            getStatus(user)
                    );
                }).collect(Collectors.toList());
            } else return Collections.emptyList();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return Collections.emptyList();
        }
    }

    private ChatEntity findChatWithTeacher(UserEntity student, UserEntity teacher) {
        for (ChatEntity chat : student.getChats()) {
            if (chat.getChatMembers().size() == 2 && chat.getChatMembers().contains(teacher)) {
                return chat;
            }
        }
        return null;
    }

    private String getStatus(UserEntity student) {
        return sessionRegistry.getStatusForUser(student.getId());
    }
}
