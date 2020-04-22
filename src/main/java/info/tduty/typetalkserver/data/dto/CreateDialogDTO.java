package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateDialogDTO {

    @SerializedName("lesson_id")
    private String lessonId;

    @SerializedName("task_id")
    private String taskId;

    @SerializedName("members")
    private List<String> members;

    public CreateDialogDTO(String lessonId, String taskId, List<String> members) {
        this.lessonId = lessonId;
        this.taskId = taskId;
        this.members = members;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        this.lessonId = lessonId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateDialogDTO that = (CreateDialogDTO) o;

        if (lessonId != null ? !lessonId.equals(that.lessonId) : that.lessonId != null) return false;
        if (taskId != null ? !taskId.equals(that.taskId) : that.taskId != null) return false;
        return members != null ? members.equals(that.members) : that.members == null;
    }

    @Override
    public int hashCode() {
        int result = lessonId != null ? lessonId.hashCode() : 0;
        result = 31 * result + (taskId != null ? taskId.hashCode() : 0);
        result = 31 * result + (members != null ? members.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreateDialogDTO{" +
                "lessonId='" + lessonId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", members=" + members +
                '}';
    }
}
