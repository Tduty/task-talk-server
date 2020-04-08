package info.tduty.typetalkserver.data.event.payload;

import com.google.gson.annotations.SerializedName;
import info.tduty.typetalkserver.data.event.EventPayload;

import java.util.List;

public class LessonPayload implements EventPayload {

    @SerializedName("id")
    private String id;

    @SerializedName("classId")
    private String classId;

    public LessonPayload() {

    }

    public LessonPayload(String id,
                         String classId) {
        this.id = id;
        this.classId = classId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonPayload that = (LessonPayload) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return classId != null ? classId.equals(that.classId) : that.classId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (classId != null ? classId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LessonPayload{" +
                "id='" + id + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}
