package info.tduty.typetalkserver.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TaskProgress")
public class TaskProgressEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private TaskEntity task;

    @ManyToOne(fetch = FetchType.EAGER)
    private LessonProgressEntity lessonProgress;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity executor;

    @Column(name="status")
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public LessonProgressEntity getLessonProgress() {
        return lessonProgress;
    }

    public void setLessonProgress(LessonProgressEntity lessonProgress) {
        this.lessonProgress = lessonProgress;
    }

    public UserEntity getExecutor() {
        return executor;
    }

    public void setExecutor(UserEntity executor) {
        this.executor = executor;
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

        TaskProgressEntity that = (TaskProgressEntity) o;

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
        return "TaskProgressJpaRepository{" +
                "id='" + id + '\'' +
                ", status=" + status +
                '}';
    }
}
