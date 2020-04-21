package info.tduty.typetalkserver.data.event.payload;

import com.google.gson.annotations.SerializedName;
import info.tduty.typetalkserver.data.event.EventPayload;

public class CorrectionPayload implements EventPayload {

    @SerializedName("sync_id")
    private String syncId;

    @SerializedName("chat_id")
    private String chatId;

    @SerializedName("additional_type")
    private Integer additionalType;

    @SerializedName("additional")
    private String additional;

    public CorrectionPayload() {

    }

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId;
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

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CorrectionPayload that = (CorrectionPayload) o;

        if (syncId != null ? !syncId.equals(that.syncId) : that.syncId != null) return false;
        if (chatId != null ? !chatId.equals(that.chatId) : that.chatId != null) return false;
        if (additionalType != null ? !additionalType.equals(that.additionalType) : that.additionalType != null)
            return false;
        return additional != null ? additional.equals(that.additional) : that.additional == null;
    }

    @Override
    public int hashCode() {
        int result = syncId != null ? syncId.hashCode() : 0;
        result = 31 * result + (chatId != null ? chatId.hashCode() : 0);
        result = 31 * result + (additionalType != null ? additionalType.hashCode() : 0);
        result = 31 * result + (additional != null ? additional.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CorrectionPayload{" +
                "syncId='" + syncId + '\'' +
                ", chatId='" + chatId + '\'' +
                ", additionalType=" + additionalType +
                ", additional='" + additional + '\'' +
                '}';
    }
}
