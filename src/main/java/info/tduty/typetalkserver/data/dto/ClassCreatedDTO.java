package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ClassCreatedDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("teacher_id")
    private String teacherId;

    @SerializedName("users")
    private List<CreateClassDTO.UserDTO> users;

    public ClassCreatedDTO(String id, String title, String teacherId, List<CreateClassDTO.UserDTO> users) {
        this.id = id;
        this.title = title;
        this.teacherId = teacherId;
        this.users = users;
    }

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

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public List<CreateClassDTO.UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<CreateClassDTO.UserDTO> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassCreatedDTO that = (ClassCreatedDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        return users != null ? users.equals(that.users) : that.users == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ClassCreatedDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", users=" + users +
                '}';
    }
}
