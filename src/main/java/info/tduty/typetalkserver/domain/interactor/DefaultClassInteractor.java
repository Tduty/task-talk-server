package info.tduty.typetalkserver.domain.interactor;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import info.tduty.typetalkserver.data.entity.*;
import info.tduty.typetalkserver.domain.mapper.ContentTaskMockHelper;
import info.tduty.typetalkserver.domain.mapper.object.DictionaryObject;
import info.tduty.typetalkserver.repository.wrapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class DefaultClassInteractor {

    private final static Gson gson = new Gson();
    private ClassWrapper classWrapper;
    private UserWrapper userWrapper;
    private ChatWrapper chatWrapper;
    private LessonWrapper lessonWrapper;
    private DictionaryWrapper dictionaryWrapper;
    private PasswordEncoder passwordEncoder;
    private LessonInteractor lessonInteractor;

    @Autowired
    public DefaultClassInteractor(ClassWrapper classWrapper,
                                  UserWrapper userWrapper,
                                  ChatWrapper chatWrapper,
                                  LessonWrapper lessonWrapper,
                                  DictionaryWrapper dictionaryWrapper,
                                  PasswordEncoder passwordEncoder,
                                  LessonInteractor lessonInteractor) {
        this.classWrapper = classWrapper;
        this.userWrapper = userWrapper;
        this.chatWrapper = chatWrapper;
        this.lessonWrapper = lessonWrapper;
        this.dictionaryWrapper = dictionaryWrapper;
        this.passwordEncoder = passwordEncoder;
        this.lessonInteractor = lessonInteractor;
        init();
    }

    private void init() {
        UserEntity teacher = userWrapper.add(createTeacher());
        ClassEntity classEntity = classWrapper.add(createClass(teacher));
        Iterable<UserEntity> users = userWrapper.addList(createUsers(classEntity));
        List<UserEntity> allUsers = new ArrayList<>();
        allUsers.add(teacher);
        users.forEach(allUsers::add);

        generateClassChat(classEntity, allUsers);
        generateTeacherChats(classEntity, allUsers);
        generateLesson(classEntity);
    }

    private ClassEntity createClass(UserEntity teacher) {
        ClassEntity classEntity = new ClassEntity();
        classEntity.setTitle("Test class");
        classEntity.setAvatar("test");
        classEntity.setTeacher(teacher);
        return classEntity;
    }

    private UserEntity createTeacher() {
        return createUser(null, "Teacher", "12345", "female", true);
    }

    private List<UserEntity> createUsers(ClassEntity classEntity) {
        ArrayList<UserEntity> list = new ArrayList<>();
        list.add(createUser(classEntity, "Test1", "12345", "male"));
        list.add(createUser(classEntity, "Test2", "12345", "female"));
        list.add(createUser(classEntity, "Test3", "12345", "female"));
        list.add(createUser(classEntity, "Test4", "12345", "female"));
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
        entity.setConnected(false);
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

    private void generateLesson(ClassEntity classEntity) {
        String lessonId = generateLesson();
        lessonInteractor.addLessonToClass(classEntity.getId(), lessonId);
    }

    private String generateLesson() {
        LessonEntity lesson = new LessonEntity();
        lesson.setTitle("Health&Safety");
        lesson.setDescription("Description");
        LessonEntity lessonNew = lessonWrapper.save(lesson);
        List<DictionaryEntity> dictionaryEntities = generateDictionaries(lessonNew);
        generateTasks(lessonNew, dictionaryEntities);
        return lessonNew.getId();
    }

    private void generateTasks(LessonEntity lesson, List<DictionaryEntity> dictionaryEntities) {
        List<TaskEntity> tasks = new ArrayList<>();
        tasks.add(generateTask("Flashcards", ContentTaskMockHelper.getFlashcardTaskContent(dictionaryEntities), 0, "flashcards", lesson));
        tasks.add(generateTask("Wordamess?", ContentTaskMockHelper.getWordamessContent(dictionaryEntities), 1, "wordamess", lesson));
        tasks.add(generateTask("Hurry up", ContentTaskMockHelper.getHurryUpTask(dictionaryEntities), 2, "hurry_up", lesson));
        tasks.add(generateTask("Phrase-Building", ContentTaskMockHelper.getPhraseBuilderTaslContent(), 3, "phrase_building", lesson));
        tasks.add(generateTask("Translation", ContentTaskMockHelper.getTranslationTaskContent(), 4, "translation", lesson));
        tasks.add(generateTask("Dictionary Pictionary", ContentTaskMockHelper.getDictionaryPictionaryTaskContent(), 5, "dictionary_pictionary", lesson));
        tasks.add(generateTask("Dialog", ContentTaskMockHelper.getDialogWithUnknownContent(), 6, "dialog_with_unknown", lesson));
        lessonWrapper.saveTasks(tasks);
    }

    private TaskEntity generateTask(String title, String content, int postion, String type, LessonEntity lesson) {
        TaskEntity task = new TaskEntity();
        task.setTitle(title);
        task.setContent(content);
        task.setPosition(postion);
        task.setType(type);
        task.setLesson(lesson);
        return task;
    }

    private List<DictionaryEntity>  generateDictionaries(LessonEntity lesson) {
        List<DictionaryEntity> dictionaries = new ArrayList<>();
        try {
            Type type = new TypeToken<List<DictionaryObject>>() {
            }.getType();
            String json = getJsonString("private/DictionaryJson");
            List<DictionaryObject> dictionaryObjects = gson.fromJson(json, type);
            dictionaries = mapDictionaryObjToEntity(dictionaryObjects, lesson);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            dictionaries = getMockDictionary(lesson);
        }

        dictionaryWrapper.save(dictionaries);
        return dictionaries;
    }

    private List<DictionaryEntity> mapDictionaryObjToEntity(List<DictionaryObject> dictionaryObjects, LessonEntity lesson) {
        List<DictionaryEntity> dictionaries = new ArrayList<>();
        dictionaryObjects.forEach(dictionaryObject ->
                dictionaries.add(generateDictionary(dictionaryObject.getContent(), dictionaryObject.getTranscription(),
                        dictionaryObject.getTranslation(),
                        lesson))
        );
        return dictionaries;
    }

    private List<DictionaryEntity> getMockDictionary(LessonEntity lesson) {
        List<DictionaryEntity> dictionaries = new ArrayList<>();
        dictionaries.add(generateDictionary("content", "transcription", "translation", lesson));
        dictionaries.add(generateDictionary("content1", "transcription1", "translation1", lesson));
        dictionaries.add(generateDictionary("content2", "transcription2", "translation2", lesson));
        dictionaries.add(generateDictionary("content3", "transcription3", "translation3", lesson));
        dictionaries.add(generateDictionary("content4", "transcription4", "translation4", lesson));
        dictionaries.add(generateDictionary("content5", "transcription5", "translation5", lesson));
        return dictionaries;
    }

    private DictionaryEntity generateDictionary(String content, String transcription, String translation, LessonEntity lesson) {
        DictionaryEntity dictionary = new DictionaryEntity();
        dictionary.setContent(content);
        dictionary.setTranscription(transcription);
        dictionary.setTranslation(translation);
        dictionary.setLesson(lesson);
        return dictionary;
    }

    public static String getJsonString(String path) {
        try (InputStream stream = new ClassPathResource(path).getInputStream()) {
            return new BufferedReader(new InputStreamReader(stream)).lines()
                    .parallel().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
