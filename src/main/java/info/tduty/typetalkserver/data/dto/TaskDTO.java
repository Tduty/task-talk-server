package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

public class TaskDTO {

    @SerializedName("icon")
    private String icon;

    @SerializedName("title")
    private String title;

    @SerializedName("status")
    private int status;

    public TaskDTO(String icon, String title, int status) {
        this.icon = icon;
        this.title = title;
        this.status = status;
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
        if (icon != null ? !icon.equals(taskDTO.icon) : taskDTO.icon != null) return false;
        return title != null ? title.equals(taskDTO.title) : taskDTO.title == null;
    }

    @Override
    public int hashCode() {
        int result = icon != null ? icon.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", status=" + status +
                '}';
    }
}
