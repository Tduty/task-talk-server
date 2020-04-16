package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

public class TaskDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("icon")
    private String icon;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    @SerializedName("position")
    private int position;

    @SerializedName("status")
    private int status;

    @SerializedName("payload")
    private String payload;

    public TaskDTO(String id, String icon, String title, String type, int position, int status, String payload) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.type = type;
        this.position = position;
        this.status = status;
        this.payload = payload;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskDTO taskDTO = (TaskDTO) o;

        if (position != taskDTO.position) return false;
        if (status != taskDTO.status) return false;
        if (id != null ? !id.equals(taskDTO.id) : taskDTO.id != null) return false;
        if (icon != null ? !icon.equals(taskDTO.icon) : taskDTO.icon != null) return false;
        if (title != null ? !title.equals(taskDTO.title) : taskDTO.title != null) return false;
        if (type != null ? !type.equals(taskDTO.type) : taskDTO.type != null) return false;
        return payload != null ? payload.equals(taskDTO.payload) : taskDTO.payload == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + position;
        result = 31 * result + status;
        result = 31 * result + (payload != null ? payload.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id='" + id + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", position=" + position +
                ", status=" + status +
                ", payload='" + payload + '\'' +
                '}';
    }
}
