package info.tduty.typetalkserver.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Message")
public class MessageEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="sync_id", unique = true)
    private String syncId;

    @Column(name="content")
    private String content;

    @Column(name="avatar_url")
    private String avatar;

    @Column(name="additional_type")
    private Integer additionalType;

    @Column(name="additional")
    private String additional;

    @Column(name="time")
    private long time;

    @ManyToOne(fetch = FetchType.EAGER)
    private ChatEntity chat;

    @ManyToOne(fetch = FetchType.EAGER)
    private UserEntity sender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSyncId() {
        return syncId;
    }

    public void setSyncId(String syncId) {
        this.syncId = syncId;
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

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
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

    public Integer getAdditionalType() {
        return additionalType;
    }

    public void setAdditionalType(Integer additionalType) {
        this.additionalType = additionalType;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageEntity that = (MessageEntity) o;

        if (time != that.time) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (syncId != null ? !syncId.equals(that.syncId) : that.syncId != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (avatar != null ? !avatar.equals(that.avatar) : that.avatar != null) return false;
        if (additionalType != null ? !additionalType.equals(that.additionalType) : that.additionalType != null)
            return false;
        if (additional != null ? !additional.equals(that.additional) : that.additional != null) return false;
        if (chat != null ? !chat.equals(that.chat) : that.chat != null) return false;
        return sender != null ? sender.equals(that.sender) : that.sender == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (syncId != null ? syncId.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (avatar != null ? avatar.hashCode() : 0);
        result = 31 * result + (additionalType != null ? additionalType.hashCode() : 0);
        result = 31 * result + (additional != null ? additional.hashCode() : 0);
        result = 31 * result + (int) (time ^ (time >>> 32));
        result = 31 * result + (chat != null ? chat.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "id='" + id + '\'' +
                ", syncId='" + syncId + '\'' +
                ", content='" + content + '\'' +
                ", avatar='" + avatar + '\'' +
                ", additionalType=" + additionalType +
                ", additional='" + additional + '\'' +
                ", time=" + time +
                ", chat=" + chat +
                ", sender=" + sender +
                '}';
    }
}
