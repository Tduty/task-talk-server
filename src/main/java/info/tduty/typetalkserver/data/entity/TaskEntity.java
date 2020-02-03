package info.tduty.typetalkserver.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "Task")
public class TaskEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="avatar_url")
    private String avatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "lesson",
            joinColumns = @JoinColumn(name = "ID_TASK"),
            inverseJoinColumns = @JoinColumn(name = "ID_LESSON")
    )
    private LessonEntity lesson;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        return lesson != null ? lesson.equals(that.lesson) : that.lesson == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (lesson != null ? lesson.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", avatar='" + avatar + '\'' +
                ", lesson=" + lesson +
                '}';
    }
}
