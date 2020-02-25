package info.tduty.typetalkserver.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Chat")
public class ChatEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="avatar_url")
    private String avatar;

    @OneToMany(mappedBy="chat", fetch = FetchType.LAZY)
    private Set<MessageEntity> messages;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "User_to_Chat",
            joinColumns = { @JoinColumn(name = "ID_CHAT") },
            inverseJoinColumns = { @JoinColumn(name = "ID_USER") }
    )
    private List<UserEntity> chatMembers;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClassEntity classEntity;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Set<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(Set<MessageEntity> messages) {
        this.messages = messages;
    }

    public List<UserEntity> getChatMembers() {
        return chatMembers;
    }

    public void setChatMembers(List<UserEntity> chatMembers) {
        this.chatMembers = chatMembers;
    }

    public ClassEntity getClassEntity() {
        return classEntity;
    }

    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatEntity that = (ChatEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        return classEntity != null ? classEntity.equals(that.classEntity) : that.classEntity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (classEntity != null ? classEntity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChatEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", avatar='" + avatar + '\'' +
                ", classEntity=" + classEntity +
                '}';
    }
}
