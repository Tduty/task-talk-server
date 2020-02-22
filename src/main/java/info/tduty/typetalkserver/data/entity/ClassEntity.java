package info.tduty.typetalkserver.data.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Class")
public class ClassEntity {

    @Id
    @GeneratedValue
    private String id;

    @Column(name="title", unique = true)
    private String title;

    @Column(name="avatar")
    private String avatar;

    @OneToMany(mappedBy="classEntity", fetch = FetchType.LAZY)
    private List<UserEntity> students;

    @OneToMany(mappedBy="classEntity", fetch = FetchType.LAZY)
    private List<ChatEntity> chats;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Class_to_Lesson",
            joinColumns = { @JoinColumn(name = "ID_CLASS") },
            inverseJoinColumns = { @JoinColumn(name = "ID_LESSON") }
    )
    private Set<LessonEntity> lessons;

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

    public List<UserEntity> getStudents() {
        return students;
    }

    public void setStudents(List<UserEntity> students) {
        this.students = students;
    }

    public List<ChatEntity> getChats() {
        return chats;
    }

    public void setChats(List<ChatEntity> chats) {
        this.chats = chats;
    }

    public Set<LessonEntity> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonEntity> lessons) {
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassEntity that = (ClassEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (students != null ? !students.equals(that.students) : that.students != null) return false;
        if (chats != null ? !chats.equals(that.chats) : that.chats != null) return false;
        return lessons != null ? lessons.equals(that.lessons) : that.lessons == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (students != null ? students.hashCode() : 0);
        result = 31 * result + (chats != null ? chats.hashCode() : 0);
        result = 31 * result + (lessons != null ? lessons.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", avatar='" + avatar + '\'' +
                ", students=" + students +
                ", chats=" + chats +
                ", lessons=" + lessons +
                '}';
    }
}
