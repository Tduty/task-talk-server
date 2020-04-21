package info.tduty.typetalkserver.data.event.payload;

import com.google.gson.annotations.SerializedName;
import info.tduty.typetalkserver.data.event.EventPayload;

public class MessageNewPayload implements EventPayload {

    @SerializedName("id")
    private String id;

    @SerializedName("chat_id")
    private String chatId;

    @SerializedName("sender_id")
    private String senderId;

    @SerializedName("sender_name")
    private String senderName;

    @SerializedName("sender_type")
    private String senderType;

    @SerializedName("body")
    private String body;

    @SerializedName("additional_type")
    private Integer additionalType;

    @SerializedName("additional")
    private String additional;

    @SerializedName("sending_time")
    private Long sendingTime; //TODO: перейти к timestamp

    public MessageNewPayload() {

    }

    public MessageNewPayload(String id,
                             String chatId,
                             String senderId,
                             String senderName,
                             String senderType,
                             String body,
                             Integer additionalType,
                             String additional,
                             Long sendingTime) {
        this.id = id;
        this.chatId = chatId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.senderType = senderType;
        this.body = body;
        this.additionalType = additionalType;
        this.additional = additional;
        this.sendingTime = sendingTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderType() {
        return senderType;
    }

    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getSendingTime() {
        return sendingTime;
    }

    public void setSendingTime(Long sendingTime) {
        this.sendingTime = sendingTime;
    }

    public Integer getAdditionalType() {
        return additionalType;
    }

    public void setAdditionalType(Integer additionalType) {
        this.additionalType = additionalType;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageNewPayload that = (MessageNewPayload) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (chatId != null ? !chatId.equals(that.chatId) : that.chatId != null) return false;
        if (senderId != null ? !senderId.equals(that.senderId) : that.senderId != null) return false;
        if (senderName != null ? !senderName.equals(that.senderName) : that.senderName != null) return false;
        if (senderType != null ? !senderType.equals(that.senderType) : that.senderType != null) return false;
        if (body != null ? !body.equals(that.body) : that.body != null) return false;
        if (additionalType != null ? !additionalType.equals(that.additionalType) : that.additionalType != null)
            return false;
        if (additional != null ? !additional.equals(that.additional) : that.additional != null) return false;
        return sendingTime != null ? sendingTime.equals(that.sendingTime) : that.sendingTime == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        result = 31 * result + (senderId != null ? senderId.hashCode() : 0);
        result = 31 * result + (senderName != null ? senderName.hashCode() : 0);
        result = 31 * result + (senderType != null ? senderType.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (additionalType != null ? additionalType.hashCode() : 0);
        result = 31 * result + (additional != null ? additional.hashCode() : 0);
        result = 31 * result + (sendingTime != null ? sendingTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageNewPayload{" +
                "id='" + id + '\'' +
                ", chatId='" + chatId + '\'' +
                ", senderId='" + senderId + '\'' +
                ", senderName='" + senderName + '\'' +
                ", senderType='" + senderType + '\'' +
                ", body='" + body + '\'' +
                ", additionalType=" + additionalType +
                ", additional='" + additional + '\'' +
                ", sendingTime=" + sendingTime +
                '}';
    }
}
