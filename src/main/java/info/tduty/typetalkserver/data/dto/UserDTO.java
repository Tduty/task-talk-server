package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

public class UserDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("class_name")
    private String className;

    @SerializedName("teacher")
    private boolean isTeacher;

    public UserDTO(String id, String name, String className, boolean isTeacher) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.isTeacher = isTeacher;
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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (isTeacher != userDTO.isTeacher) return false;
        if (id != null ? !id.equals(userDTO.id) : userDTO.id != null) return false;
        if (name != null ? !name.equals(userDTO.name) : userDTO.name != null) return false;
        return className != null ? className.equals(userDTO.className) : userDTO.className == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (className != null ? className.hashCode() : 0);
        result = 31 * result + (isTeacher ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", isTeacher=" + isTeacher +
                '}';
    }
}
