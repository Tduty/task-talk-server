package info.tduty.typetalkserver.data.event.payload;

import com.google.gson.annotations.SerializedName;
import info.tduty.typetalkserver.data.event.EventPayload;

public class UserStatusPayload implements EventPayload {

    @SerializedName("user_id")
    private String userId;

    @SerializedName("status")
    private String status;

    public UserStatusPayload() {

    }

    public UserStatusPayload(String userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getState() {
        return status;
    }

    public void setState(String state) {
        this.status = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserStatusPayload that = (UserStatusPayload) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return status != null ? status.equals(that.status) : that.status == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserStatusPayload{" +
                "userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
