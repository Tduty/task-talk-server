package info.tduty.typetalkserver.domain.interactor;

import info.tduty.typetalkserver.data.entity.ChatEntity;
import info.tduty.typetalkserver.data.entity.ClassEntity;
import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.repository.wrapper.ChatWrapper;
import info.tduty.typetalkserver.repository.wrapper.ClassWrapper;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DefaultClassInteractor {

    private ClassWrapper classWrapper;
    private UserWrapper userWrapper;
    private ChatWrapper chatWrapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DefaultClassInteractor(ClassWrapper classWrapper, UserWrapper userWrapper,
                                  ChatWrapper chatWrapper, PasswordEncoder passwordEncoder) {
        this.classWrapper = classWrapper;
        this.userWrapper = userWrapper;
        this.chatWrapper = chatWrapper;
        this.passwordEncoder = passwordEncoder;
        init();
    }

    private void init() {
        ClassEntity classEntity = classWrapper.add(createClass());
        Iterable<UserEntity> users = userWrapper.addList(createUsers(classEntity));

        generateClassChat(classEntity, users);
        generateTeacherChats(classEntity, users);
    }

    private ClassEntity createClass() {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setTitle("Test class");
        classEntity.setAvatar("test");
        return classEntity;
    }

    private List<UserEntity> createUsers(ClassEntity classEntity) {
        ArrayList<UserEntity> list = new ArrayList<>();
        list.add(createUser(classEntity, "Teacher", "12345", "female", true));
        list.add(createUser(classEntity, "Test1", "12345", "male"));
        list.add(createUser(classEntity, "Test2", "12345", "male"));
        list.add(createUser(classEntity, "Test3", "12345", "male"));
        list.add(createUser(classEntity, "Test4", "12345", "male"));
        list.add(createUser(classEntity, "Test5", "12345", "male"));
        list.add(createUser(classEntity, "Test6", "12345", "male"));
        list.add(createUser(classEntity, "Test7", "12345", "male"));
        list.add(createUser(classEntity, "Test8", "12345", "male"));
        return list;
    }

    private UserEntity createUser(ClassEntity classEntity, String name, String password,
                                  String sex, boolean isTeacher) {
        UserEntity entity = new UserEntity();
        entity.setName(name);
        entity.setPassword(passwordEncoder.encode(password));
        entity.setSex(sex);
        entity.setTeacher(isTeacher);
        entity.setClassEntity(classEntity);
        entity.setEnabled(true);
        return entity;
    }

    private UserEntity createUser(ClassEntity classEntity, String name, String password,
                                  String sex) {
        return createUser(classEntity, name, password, sex, false);
    }

    private void generateClassChat(ClassEntity classEntity, Iterable<UserEntity> userEntities) {
        ChatEntity chat = new ChatEntity();
        Set<UserEntity> members = new HashSet<>();
        userEntities.forEach(members::add);
        chat.setChatMembers(members);
        chat.setTitle("Class chat");
        chat.setType("class_chat");
        chat.setClassEntity(classEntity);
        chatWrapper.add(chat);
    }

    private void generateTeacherChats(ClassEntity classEntity, Iterable<UserEntity> userEntities) {
        UserEntity teacher = null;
        for (UserEntity user : userEntities) {
            if (user.getTeacher()) teacher = user;
        }
        if (teacher == null) throw new IllegalArgumentException("Class not have teacher");
        for (UserEntity user : userEntities) {
            if (user.getTeacher()) continue;
            ChatEntity chat = new ChatEntity();
            Set<UserEntity> members = new HashSet<>();
            members.add(teacher);
            members.add(user);
            chat.setChatMembers(members);
            chat.setTitle(user.getName());
            chat.setType("teacher_chat");
            chat.setClassEntity(classEntity);
            chatWrapper.add(chat);
        }
    }
}
