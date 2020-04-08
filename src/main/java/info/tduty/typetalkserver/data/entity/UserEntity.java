package info.tduty.typetalkserver.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "User")
public class UserEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="name", unique = true)
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="enabled")
    private Boolean enabled;

    @Column(name="sex")
    private String sex;

    @Column(name="teacher")
    private Boolean teacher;

    @OneToMany(mappedBy = "sender", fetch = FetchType.EAGER)
    private Set<MessageEntity> messages;

    @ManyToMany(mappedBy="chatMembers", fetch = FetchType.EAGER)
    private Set<ChatEntity> chats;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClassEntity classEntity;

    @OneToMany(mappedBy = "executor", fetch = FetchType.EAGER)
    private Set<LessonProgressEntity> lessons;

    @OneToMany(mappedBy = "executor", fetch = FetchType.EAGER)
    private Set<TaskProgressEntity> tasks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Boolean getTeacher() {
        return teacher;
    }

    public void setTeacher(Boolean teacher) {
        this.teacher = teacher;
    }

    public Set<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
    }

    public Set<ChatEntity> getChats() {
        return chats;
    }

    public void setChats(Set<ChatEntity> chats) {
        this.chats = chats;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    public Set<LessonProgressEntity> getLessons() {
        return lessons;
    }

    public void setLessons(Set<LessonProgressEntity> lessons) {
        this.lessons = lessons;
    }

    public Set<TaskProgressEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskProgressEntity> tasks) {
        this.tasks = tasks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        return teacher != null ? !teacher.equals(that.teacher) : that.teacher != null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", sex='" + sex + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
