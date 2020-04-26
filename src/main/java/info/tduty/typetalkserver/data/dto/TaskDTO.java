package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

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

    @SerializedName("optional")
    private boolean optional;

    @SerializedName("payload")
    private String payload;

    public TaskDTO(String id, String icon, String title, String type, int position, int status, boolean optional, String payload) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.type = type;
        this.position = position;
        this.status = status;
        this.optional = optional;
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

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) o;
        return position == taskDTO.position &&
                status == taskDTO.status &&
                optional == taskDTO.optional &&
                Objects.equals(id, taskDTO.id) &&
                Objects.equals(icon, taskDTO.icon) &&
                Objects.equals(title, taskDTO.title) &&
                Objects.equals(type, taskDTO.type) &&
                Objects.equals(payload, taskDTO.payload);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, icon, title, type, position, status, optional, payload);
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
                ", optional=" + optional +
                ", payload='" + payload + '\'' +
                '}';
    }
}
