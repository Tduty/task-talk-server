package info.tduty.typetalkserver.data.event.payload;

import com.google.gson.annotations.SerializedName;
import info.tduty.typetalkserver.data.event.EventPayload;

import java.util.Objects;

public class LessonProgressPayload implements EventPayload {

    @SerializedName("lesson_id")
    private String lessonId;
    @SerializedName("state")
    private Integer state;

    public LessonProgressPayload(String lessonId, Integer state) {
        this.lessonId = lessonId;
        this.state = state;
    }

    public String getLessonId() {
        return lessonId;
    }

    public Integer getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonProgressPayload that = (LessonProgressPayload) o;
        return lessonId.equals(that.lessonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId);
    }

    @Override
    public String toString() {
        return "LessonProgressPayload{" +
                "lessonId='" + lessonId + '\'' +
                ", state=" + state +
                '}';
    }
}
