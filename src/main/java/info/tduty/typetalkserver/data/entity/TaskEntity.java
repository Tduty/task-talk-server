package info.tduty.typetalkserver.data.entity;

import com.google.gson.annotations.SerializedName;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

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

    @Column(name="content",columnDefinition="LONGTEXT")
    private String content;

    @SerializedName("type")
    private String type;

    @SerializedName("position")
    private int position;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskEntity that = (TaskEntity) o;

        if (position != that.position) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        return type != null ? !type.equals(that.type) : that.type != null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + position;
        return result;
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
                '}';
    }
}
