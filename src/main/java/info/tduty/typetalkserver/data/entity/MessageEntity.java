package info.tduty.typetalkserver.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "Message")
public class MessageEntity {

    @Id
    @GeneratedValue
    private String id;

    @Column(name="content")
    private String content;

    @Column(name="avatar_url")
    private String avatar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "chat",
            joinColumns = @JoinColumn(name = "ID_MESSAGE"),
            inverseJoinColumns = @JoinColumn(name = "ID_CHAT")
    )
    private ChatEntity chat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "sender",
            joinColumns = @JoinColumn(name = "ID_MESSAGE"),
            inverseJoinColumns = @JoinColumn(name = "ID_SENDER")
    )
    private UserEntity sender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public ChatEntity getChat() {
        return chat;
    }

    public void setChat(ChatEntity chat) {
        this.chat = chat;
    }

    public UserEntity getSender() {
        return sender;
    }

    public void setSender(UserEntity sender) {
        this.sender = sender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (chat != null ? !chat.equals(that.chat) : that.chat != null) return false;
        return sender != null ? sender.equals(that.sender) : that.sender == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (chat != null ? chat.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", chat=" + chat +
                ", sender=" + sender +
                '}';
    }
}
