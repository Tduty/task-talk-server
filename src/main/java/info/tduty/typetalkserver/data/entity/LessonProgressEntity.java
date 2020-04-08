package info.tduty.typetalkserver.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "LessonProgress")
public class LessonProgressEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private LessonEntity lesson;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity executor;

    @OneToMany(mappedBy = "lessonProgress", fetch = FetchType.EAGER)
    private Set<TaskProgressEntity> tasksProgress;

    @Column(name="status")
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LessonEntity getLesson() {
        return lesson;
    }

    public void setLesson(LessonEntity lesson) {
        this.lesson = lesson;
    }

    public UserEntity getExecutor() {
        return executor;
    }

    public void setExecutor(UserEntity executor) {
        this.executor = executor;
    }

    public Set<TaskProgressEntity> getTasksProgress() {
        return tasksProgress;
    }

    public void setTasksProgress(Set<TaskProgressEntity> tasksProgress) {
        this.tasksProgress = tasksProgress;
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

        LessonProgressEntity that = (LessonProgressEntity) o;

        if (status != that.status) return false;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "LessonProgressEntity{" +
                "id='" + id + '\'' +
                ", status=" + status +
                '}';
    }
}
