package info.tduty.typetalkserver.data.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Lesson")
public class LessonEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="avatar_url")
    private String avatar;

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy="lessons")
    private List<ClassEntity> classes;

    @ManyToMany(mappedBy="completedLessons")
    private List<UserEntity> completed;

    @ManyToMany(mappedBy="executeLessons")
    private List<UserEntity> execute;

    @OneToMany(mappedBy="lesson", fetch = FetchType.LAZY)
    private List<TaskEntity> tasks;

    @OneToMany(mappedBy="lesson", fetch = FetchType.LAZY)
    private List<DictionaryEntity> dictionaries;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassEntity> classes) {
        this.classes = classes;
    }

    public List<UserEntity> getCompleted() {
        return completed;
    }

    public void setCompleted(List<UserEntity> completed) {
        this.completed = completed;
    }

    public List<UserEntity> getExecute() {
        return execute;
    }

    public void setExecute(List<UserEntity> execute) {
        this.execute = execute;
    }

    public List<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public List<DictionaryEntity> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(List<DictionaryEntity> dictionaries) {
        this.dictionaries = dictionaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonEntity that = (LessonEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (classes != null ? !classes.equals(that.classes) : that.classes != null) return false;
        if (completed != null ? !completed.equals(that.completed) : that.completed != null) return false;
        if (execute != null ? !execute.equals(that.execute) : that.execute != null) return false;
        if (tasks != null ? !tasks.equals(that.tasks) : that.tasks != null) return false;
        return dictionaries != null ? dictionaries.equals(that.dictionaries) : that.dictionaries == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (classes != null ? classes.hashCode() : 0);
        result = 31 * result + (completed != null ? completed.hashCode() : 0);
        result = 31 * result + (execute != null ? execute.hashCode() : 0);
        result = 31 * result + (tasks != null ? tasks.hashCode() : 0);
        result = 31 * result + (dictionaries != null ? dictionaries.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LessonEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", avatar='" + avatar + '\'' +
                ", description='" + description + '\'' +
                ", classes=" + classes +
                ", completed=" + completed +
                ", execute=" + execute +
                ", tasks=" + tasks +
                ", dictionaries=" + dictionaries +
                '}';
    }
}
