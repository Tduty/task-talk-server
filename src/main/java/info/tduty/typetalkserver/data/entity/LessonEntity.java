package info.tduty.typetalkserver.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Lesson")
public class LessonEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="title")
    private String title;

    @Column(name="avatar_url")
    private String avatar;

    @Column(name="description")
    private String description;

    @ManyToMany(mappedBy="lessons", fetch = FetchType.EAGER)
    private Set<ClassEntity> classes;

    @OneToMany(mappedBy="lesson", fetch = FetchType.EAGER)
    private Set<TaskEntity> tasks;

    @OneToMany(mappedBy="lesson", fetch = FetchType.EAGER)
    private Set<DictionaryEntity> dictionaries;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<ClassEntity> getClasses() {
        return classes;
    }

    public void setClasses(Set<ClassEntity> classes) {
        this.classes = classes;
    }

    public Set<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
    }

    public Set<DictionaryEntity> getDictionaries() {
        return dictionaries;
    }

    public void setDictionaries(Set<DictionaryEntity> dictionaries) {
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
        return description != null ? !description.equals(that.description) : that.description != null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LessonEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", avatar='" + avatar + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
