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

    public TaskDTO(String id, String icon, String title, String type, int position, int status) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.type = type;
        this.position = position;
        this.status = status;
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

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id='" + id + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", position=" + position +
                ", status=" + status +
                '}';
    }
}
