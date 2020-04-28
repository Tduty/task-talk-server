package info.tduty.typetalkserver.domain.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import info.tduty.typetalkserver.data.entity.DictionaryEntity;
import info.tduty.typetalkserver.domain.interactor.DefaultClassInteractor;
import info.tduty.typetalkserver.domain.mapper.object.DictionaryPictionaryObject;
import info.tduty.typetalkserver.domain.mapper.object.PhraseBuilderObject;
import info.tduty.typetalkserver.domain.mapper.object.WordamessObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class ContentTaskMockHelper {

    private final static Gson gson = new Gson();

    private static final String PATH_IMAGE = "/images";
    private static final String PATH_JSON_DATA = "/private";

    public static String getFlashcardTaskContent(List<DictionaryEntity> dictionaryEntities) {
        List<FlashcardTask> list = new ArrayList<>();

        List<String> typeCard = List.of("rus-eng", "eng-rus");

        dictionaryEntities.forEach(obj -> {
            String type = typeCard.get((int) Math.round(Math.random()));
            if (type.equals("rus-eng")) {
                list.add(new FlashcardTask(type, obj.getTranslation(), obj.getContent()));
            } else {
                list.add(new FlashcardTask(type, obj.getContent(), obj.getTranslation()));
            }
        });
        return gson.toJson(list);
    }

    public static String getTranslationTaskContent() {
        return DefaultClassInteractor.getJsonString(PATH_JSON_DATA + "/TranslationJson");
    }

    public static String getDictionaryPictionaryTaskContent() {
        String json = DefaultClassInteractor.getJsonString(PATH_JSON_DATA + "/DictionaryPictionaryJson");
        Type type = new TypeToken<List<DictionaryPictionaryObject>>() {
        }.getType();
        List<DictionaryPictionaryObject> dpObjList = gson.fromJson(json, type);

        List<DictionaryPictionaryTask> list = new ArrayList<>();
        dpObjList.forEach(obj -> {
            String uri = PATH_IMAGE + "/" + obj.getImage();
            list.add(new DictionaryPictionaryTask(uri, Collections.singletonList(obj.getTranslate())));
        });
        return gson.toJson(list);
    }

    public static String getPhraseBuilderTaslContent() {
        String json = DefaultClassInteractor.getJsonString(PATH_JSON_DATA + "/PhraseBuilderJson");
        Type type = new TypeToken<List<PhraseBuilderObject>>() {
        }.getType();
        List<PhraseBuilderObject> pbObjList = gson.fromJson(json, type);

        List<PhraseBuilderTask> list = new ArrayList<>();
        AtomicInteger id = new AtomicInteger();
        pbObjList.forEach(obj -> {
            list.add(new PhraseBuilderTask(String.valueOf(id.intValue()), obj.getPhrases()));
            id.getAndIncrement();
        });
        return gson.toJson(list);
    }

    public static String getWordamessContent(List<DictionaryEntity> dictionaryEntities) {
        String json = DefaultClassInteractor.getJsonString(PATH_JSON_DATA + "/WordamessJson");
        Type type = new TypeToken<List<WordamessObject>>() {
        }.getType();
        List<WordamessObject> wordamessObjects = gson.fromJson(json, type);
        List<DictionaryEntity> randomWords = dictionaryEntities.stream().filter(dictionary ->
                wordamessObjects.stream().anyMatch(wordamessObject -> wordamessObject.getCorrect_body().equals(dictionary.getContent()))
        ).collect(Collectors.toList());

        List<WordamessTask> list = new ArrayList<>();

        wordamessObjects.forEach(obj -> {
            list.add(new WordamessTask(true, obj.getBody(), obj.getCorrect_body()));
        });

        randomWords.forEach(obj -> {
            list.add(new WordamessTask(false, obj.getContent()));
        });

        return gson.toJson(list);
    }

    public static String getHurryUpTask(List<DictionaryEntity> dictionaryEntities) {
        List<String> typeCard = List.of("rus-eng", "eng-rus");
        List<HurryUpTask> list = new ArrayList<HurryUpTask>();

        for (int i = 0; i < dictionaryEntities.size(); i++) {
            String type = typeCard.get((int) Math.round(Math.random()));
            List<DictionaryEntity> dictionaryEntitiesForTask = getData(dictionaryEntities, i);
            List<String> anyTranslation = new ArrayList<>();
            if (type.equals("rus-eng")) {
                dictionaryEntitiesForTask.forEach(dictionaryEntity -> {
                    anyTranslation.add(dictionaryEntity.getContent());
                });
                list.add(new HurryUpTask(dictionaryEntities.get(i).getTranslation(), type, dictionaryEntities.get(i).getContent(), anyTranslation));
            } else {
                dictionaryEntitiesForTask.forEach(dictionaryEntity -> {
                    anyTranslation.add(dictionaryEntity.getTranslation());
                });
                list.add(new HurryUpTask(dictionaryEntities.get(i).getContent(), type, dictionaryEntities.get(i).getTranslation(), anyTranslation));
            }
        }

        return gson.toJson(list);
    }

    private static List<DictionaryEntity> getData(List<DictionaryEntity> dictionaryEntities, int currentIndex) {
        int countElements = dictionaryEntities.size() - 1;
        int countElementGet = 4;
        List<DictionaryEntity> getDataDictionaryEntities = new ArrayList<>();

        while (getDataDictionaryEntities.size() != countElementGet) {
            int index = (int) Math.round(Math.random() * countElements);
            if (index != currentIndex) {
                getDataDictionaryEntities.add(dictionaryEntities.get(index));
            }
        }

        return getDataDictionaryEntities;
    }

    public static String getDialogWithUnknownContent() {
        return gson.toJson(new DialogWihtUnknown("Dialog", 5));
    }

    private static class FlashcardTask {
        String type;
        String front;
        String back;

        FlashcardTask(String type, String front, String back) {
            this.type = type;
            this.front = front;
            this.back = back;
        }
    }

    public static class TranslationTask {
        String type;
        String word;
        String current_translate;


        TranslationTask(String type, String word, String current_translate) {
            this.type = type;
            this.word = word;
            this.current_translate = current_translate;
        }
    }

    private static class DictionaryPictionaryTask {
        String picture;
        List<String> translates;

        DictionaryPictionaryTask(String picture, List<String> translates) {
            this.picture = picture;
            this.translates = translates;
        }
    }

    private static class PhraseBuilderTask {
        String id;
        List<String> phrases;

        PhraseBuilderTask(String id, List<String> phrases) {
            this.id = id;
            this.phrases = phrases;
        }
    }

    private static class WordamessTask {
        boolean mistake;
        String body;
        String correct_body;

        public WordamessTask(boolean mistake, String body, String correct_body) {
            this.mistake = mistake;
            this.body = body;
            this.correct_body = correct_body;
        }

        public WordamessTask(boolean mistake, String body) {
            this.mistake = mistake;
            this.body = body;
        }
    }

    private static class HurryUpTask {
        String word;
        String type;
        String translate;
        List<String> any_translates;

        public HurryUpTask(String word, String type, String translate, List<String> any_translates) {
            this.word = word;
            this.type = type;
            this.translate = translate;
            this.any_translates = any_translates;
        }
    }

    private static class DialogWihtUnknown {

        String description;
        int count_message;

        public DialogWihtUnknown(String description, int count_message) {
            this.description = description;
            this.count_message = count_message;
        }
    }
}
