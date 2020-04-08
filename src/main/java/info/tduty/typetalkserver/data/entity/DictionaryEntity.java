package info.tduty.typetalkserver.data.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Dictionary")
public class DictionaryEntity {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @Column(name="content")
    private String content;

    @Column(name="translation")
    private String translation;

    @Column(name="transcription")
    private String transcription;

    @ManyToOne(fetch = FetchType.EAGER)
    private LessonEntity lesson;

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

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
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

        DictionaryEntity that = (DictionaryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (content != null ? !content.equals(that.content) : that.content != null) return false;
        if (translation != null ? !translation.equals(that.translation) : that.translation != null) return false;
        return transcription != null ? !transcription.equals(that.transcription) : that.transcription != null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        result = 31 * result + (transcription != null ? transcription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DictionaryEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", translation='" + translation + '\'' +
                ", transcription='" + transcription + '\'' +
                '}';
    }
}
