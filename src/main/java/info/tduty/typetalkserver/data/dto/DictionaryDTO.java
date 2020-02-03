package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

public class DictionaryDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("lesson_id")
    private String lessonId;

    @SerializedName("lesson_name")
    private String lessonName;

    @SerializedName("phrase")
    private String phrase;

    @SerializedName("translation")
    private String translation;

    @SerializedName("transcription")
    private String transcription;

    public DictionaryDTO(String id,
                         String lessonId,
                         String lessonName,
                         String phrase,
                         String translation,
                         String transcription) {
        this.id = id;
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.phrase = phrase;
        this.translation = translation;
        this.transcription = transcription;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DictionaryDTO that = (DictionaryDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (lessonId != null ? !lessonId.equals(that.lessonId) : that.lessonId != null) return false;
        if (lessonName != null ? !lessonName.equals(that.lessonName) : that.lessonName != null) return false;
        if (phrase != null ? !phrase.equals(that.phrase) : that.phrase != null) return false;
        if (translation != null ? !translation.equals(that.translation) : that.translation != null) return false;
        return transcription != null ? transcription.equals(that.transcription) : that.transcription == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lessonId != null ? lessonId.hashCode() : 0);
        result = 31 * result + (lessonName != null ? lessonName.hashCode() : 0);
        result = 31 * result + (phrase != null ? phrase.hashCode() : 0);
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        result = 31 * result + (transcription != null ? transcription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DictionaryDTO{" +
                "id='" + id + '\'' +
                ", lessonId='" + lessonId + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", phrase='" + phrase + '\'' +
                ", translation='" + translation + '\'' +
                ", transcription='" + transcription + '\'' +
                '}';
    }
}
