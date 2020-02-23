package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CreateClassDTO {

    @SerializedName("title")
    private String title;

    @SerializedName("teacher_id")
    private String teacherId;

    @SerializedName("users")
    private List<UserDTO> users;

    public CreateClassDTO() {

    }

    public CreateClassDTO(String title, String teacherId, List<UserDTO> users) {
        this.title = title;
        this.teacherId = teacherId;
        this.users = users;
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

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CreateClassDTO that = (CreateClassDTO) o;

        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (teacherId != null ? !teacherId.equals(that.teacherId) : that.teacherId != null) return false;
        return users != null ? users.equals(that.users) : that.users == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
        result = 31 * result + (users != null ? users.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CreateClassDTO{" +
                "title='" + title + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", users=" + users +
                '}';
    }

    public static class UserDTO {

        @SerializedName("id")
        private String id;

        @SerializedName("name")
        private String name;

        @SerializedName("sex")
        private String sex;

        public UserDTO() {

        }

        public UserDTO(String id, String name, String sex) {
            this.id = id;
            this.name = name;
            this.sex = sex;
        }

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

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            UserDTO userDTO = (UserDTO) o;

            if (id != null ? !id.equals(userDTO.id) : userDTO.id != null) return false;
            if (name != null ? !name.equals(userDTO.name) : userDTO.name != null) return false;
            return sex != null ? sex.equals(userDTO.sex) : userDTO.sex == null;
        }

        @Override
        public int hashCode() {
            int result = id != null ? id.hashCode() : 0;
            result = 31 * result + (name != null ? name.hashCode() : 0);
            result = 31 * result + (sex != null ? sex.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "UserDTO{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", sex='" + sex + '\'' +
                    '}';
        }
    }

}
