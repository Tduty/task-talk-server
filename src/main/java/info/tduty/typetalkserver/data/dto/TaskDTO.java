package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

public class TaskDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("icon")
    private String icon;

    @SerializedName("title")
    private String title;

    @SerializedName("status")
    private int status;

    public TaskDTO(String id, String icon, String title, int status) {
        this.id = id;
        this.icon = icon;
        this.title = title;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskDTO taskDTO = (TaskDTO) o;

        if (status != taskDTO.status) return false;
        if (id != null ? !id.equals(taskDTO.id) : taskDTO.id != null) return false;
        if (icon != null ? !icon.equals(taskDTO.icon) : taskDTO.icon != null) return false;
        return title != null ? title.equals(taskDTO.title) : taskDTO.title == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id='" + id + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
