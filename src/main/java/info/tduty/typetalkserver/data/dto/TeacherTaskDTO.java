package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

public class TeacherTaskDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("lesson_id")
    private String lessonId;

    @SerializedName("icon")
    private String icon;

    @SerializedName("title")
    private String title;

    @SerializedName("type")
    private String type;

    @SerializedName("position")
    private int position;

    @SerializedName("completed")
    private int completed;

    @SerializedName("student_count")
    private int studentCount;

    public TeacherTaskDTO(String id,
                          String lessonId,
                          String icon,
                          String title,
                          String type,
                          int position,
                          int completed,
                          int studentCount) {
        this.id = id;
        this.lessonId = lessonId;
        this.icon = icon;
        this.title = title;
        this.type = type;
        this.position = position;
        this.completed = completed;
        this.studentCount = studentCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TeacherTaskDTO that = (TeacherTaskDTO) o;

        if (position != that.position) return false;
        if (completed != that.completed) return false;
        if (studentCount != that.studentCount) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (lessonId != null ? !lessonId.equals(that.lessonId) : that.lessonId != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        return type != null ? type.equals(that.type) : that.type == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (lessonId != null ? lessonId.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + position;
        result = 31 * result + completed;
        result = 31 * result + studentCount;
        return result;
    }

    @Override
    public String toString() {
        return "TeacherTaskDTO{" +
                "id='" + id + '\'' +
                ", lessonId='" + lessonId + '\'' +
                ", icon='" + icon + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", position=" + position +
                ", completed=" + completed +
                ", studentCount=" + studentCount +
                '}';
    }
}
