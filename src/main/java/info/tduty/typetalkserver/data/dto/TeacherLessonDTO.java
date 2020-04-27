package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

public class TeacherLessonDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("icon")
    private String icon;

    @SerializedName("completed")
    private int completed;

    @SerializedName("student_count")
    private int studentCount;

    @SerializedName("description")
    private String description;

    @SerializedName("lock")
    private boolean isLock;

    public TeacherLessonDTO(String id,
                            String title,
                            String icon,
                            int completed,
                            int studentCount,
                            String description,
                            boolean isLock) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.completed = completed;
        this.studentCount = studentCount;
        this.description = description;
        this.isLock = isLock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherLessonDTO that = (TeacherLessonDTO) o;

        if (completed != that.completed) return false;
        if (studentCount != that.studentCount) return false;
        if (isLock != that.isLock) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + completed;
        result = 31 * result + studentCount;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (isLock ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TeacherLessonDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", completed=" + completed +
                ", studentCount=" + studentCount +
                ", description='" + description + '\'' +
                ", isLock=" + isLock +
                '}';
    }
}
