package info.tduty.typetalkserver.data.entity;

import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Task")
public class TaskEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="title")
    private String title;

    @Column(name="avatar_url")
    private String avatar;

    @Column(name="content", columnDefinition = "LONGTEXT")
    private String content;

    @SerializedName("type")
    private String type;

    @SerializedName("position")
    private int position;

    @SerializedName("optional")
    private boolean isOptional;

    @ManyToOne(fetch = FetchType.EAGER)
    private LessonEntity lesson;

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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return position == that.position &&
                isOptional == that.isOptional &&
                Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(avatar, that.avatar) &&
                Objects.equals(content, that.content) &&
                Objects.equals(type, that.type) &&
                Objects.equals(lesson, that.lesson);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, avatar, content, type, position, isOptional, lesson);
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", avatar='" + avatar + '\'' +
                ", content='" + content + '\'' +
                ", type='" + type + '\'' +
                ", position=" + position +
                ", isOptional=" + isOptional +
                ", lesson=" + lesson +
                '}';
    }
}
