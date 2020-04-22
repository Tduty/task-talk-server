package info.tduty.typetalkserver.domain.mapper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContentTaskMockHelper {

    private final static Gson gson = new Gson();

    public static String getFlashcardTaskContent() {
        List<FlashcardTask> list = new ArrayList<>();
        list.add(new FlashcardTask("eng-rus", "test1", "тест1"));
        list.add(new FlashcardTask("eng-rus", "test2", "тест2"));
        list.add(new FlashcardTask("eng-rus", "test3", "тест3"));
        list.add(new FlashcardTask("rus-eng", "тест4", "test4"));
        list.add(new FlashcardTask("rus-eng", "тест5", "test5"));
        list.add(new FlashcardTask("eng-rus", "test6", "тест6"));
        list.add(new FlashcardTask("eng-rus", "test7", "тест7"));
        return gson.toJson(list);
    }

    public static String getTranslationTaskContent() {
        List<TranslationTask> list = new ArrayList<>();
        list.add(new TranslationTask("phrase", "word", "слово"));
        list.add(new TranslationTask("phrase", "teacher's word", "слово учителя"));
        list.add(new TranslationTask("sentence", "It was word", "Это было слово"));
        return gson.toJson(list);
    }

    public static String getDictionaryPictionaryTaskContent() {
        List<DictionaryPictionaryTask> list = new ArrayList<>();
        list.add(new DictionaryPictionaryTask("/images/image_task_cloud.jpg", Arrays.asList("cloud", "sky")));
        list.add(new DictionaryPictionaryTask("/images/image_task_cloud.jpg", Arrays.asList("cloud", "sky")));
        list.add(new DictionaryPictionaryTask("/images/image_task_cloud.jpg", Arrays.asList("cloud", "sky")));
        return gson.toJson(list);
    }

    public static String getPhraseBuilderTaslContent() {
        List<PhraseBuilderTask> list = new ArrayList<>();
        list.add(new PhraseBuilderTask("1", Arrays.asList("Be", "more", "attentive, please")));
        list.add(new PhraseBuilderTask("2", Arrays.asList("Be", "more", "attentive, please")));
        list.add(new PhraseBuilderTask("3", Arrays.asList("Be", "more", "attentive, please")));
        return gson.toJson(list);
    }

    public static String getWordamessContent() {
        List<WordamessTask> list = new ArrayList<>();
        list.add(new WordamessTask(true, "tesst1", "test1"));
        list.add(new WordamessTask(false, "test2"));
        list.add(new WordamessTask(true, "tesst3", "test3"));
        list.add(new WordamessTask(true, "tesst4", "test4"));
        list.add(new WordamessTask(true, "tesst5", "test5"));
        list.add(new WordamessTask(false, "test6"));
        list.add(new WordamessTask(false, "test7"));
        list.add(new WordamessTask(false, "test8"));
        list.add(new WordamessTask(false, "test9"));
        return gson.toJson(list);
    }

    public static String getHurryUpTask() {
        List<HurryUpTask> list = new ArrayList<HurryUpTask>();
        list.add(new HurryUpTask("test", "eng-rus", "тест", Arrays.asList("лала", "парам-парам", "еще что-то")));
        list.add(new HurryUpTask("test2", "eng-rus", "тест2", Arrays.asList("лала", "парам-парам", "еще что-то")));
        list.add(new HurryUpTask("test3", "rus-eng", "тест3", Arrays.asList("word", "any", "translate", "dictionary")));
        list.add(new HurryUpTask("test4", "rus-eng", "тест4", Arrays.asList("word", "any", "translate", "dictionary")));
        return gson.toJson(list);
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

    private static class TranslationTask {
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
