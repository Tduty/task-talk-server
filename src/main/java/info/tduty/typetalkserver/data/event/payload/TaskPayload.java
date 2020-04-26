package info.tduty.typetalkserver.data.event.payload;

import com.google.gson.annotations.SerializedName;
import info.tduty.typetalkserver.data.event.EventPayload;

import java.util.Objects;

public class TaskPayload implements EventPayload {

    @SerializedName("lesson_id")
    private String lessonId;
    @SerializedName("task_id")
    private String taskId;
    @SerializedName("completed")
    private Boolean isCompleted;

    public TaskPayload(String lessonId, String taskId, Boolean isCompleted) {
        this.lessonId = lessonId;
        this.taskId = taskId;
        this.isCompleted = isCompleted;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getTaskId() {
        return taskId;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskPayload that = (TaskPayload) o;
        return lessonId.equals(that.lessonId) &&
                taskId.equals(that.taskId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId, taskId);
    }

    @Override
    public String toString() {
        return "TaskPayload{" +
                "lessonId='" + lessonId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
