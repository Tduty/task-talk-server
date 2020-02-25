package info.tduty.typetalkserver.data.event.payload;

import com.google.gson.annotations.SerializedName;
import info.tduty.typetalkserver.data.event.EventPayload;

public class TypingPayload implements EventPayload {

    @SerializedName("sender_id")
    private String senderId;

    @SerializedName("chat_id")
    private String chatId;

    @SerializedName("type")
    private String type;

    public TypingPayload() {

    }

    public TypingPayload(String senderId, String chatId, String type) {
        this.senderId = senderId;
        this.chatId = chatId;
        this.type = type;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypingPayload that = (TypingPayload) o;

        if (senderId != null ? !senderId.equals(that.senderId) : that.senderId != null) return false;
        if (chatId != null ? !chatId.equals(that.chatId) : that.chatId != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = senderId != null ? senderId.hashCode() : 0;
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TypingPayload{" +
                "senderId='" + senderId + '\'' +
                ", chatId='" + chatId + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
