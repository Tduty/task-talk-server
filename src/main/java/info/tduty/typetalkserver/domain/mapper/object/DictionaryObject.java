package info.tduty.typetalkserver.domain.mapper.object;

import com.google.gson.annotations.SerializedName;

public class DictionaryObject {

    @SerializedName("content")
    private String content;
    @SerializedName("translation")
    private String translation;
    @SerializedName("transcription")
    private String transcription;

    public DictionaryObject(String content, String translation, String transcription) {
        this.content = content;
        this.translation = translation;
        this.transcription = transcription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
