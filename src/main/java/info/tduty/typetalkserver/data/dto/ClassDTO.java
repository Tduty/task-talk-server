package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

public class ClassDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("member_count")
    private int memberCount;

    public ClassDTO(String id, String title, String description, int memberCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.memberCount = memberCount;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClassDTO classDTO = (ClassDTO) o;

        if (memberCount != classDTO.memberCount) return false;
        if (id != null ? !id.equals(classDTO.id) : classDTO.id != null) return false;
        if (title != null ? !title.equals(classDTO.title) : classDTO.title != null) return false;
        return description != null ? description.equals(classDTO.description) : classDTO.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + memberCount;
        return result;
    }

    @Override
    public String toString() {
        return "ClassDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", memberCount=" + memberCount +
                '}';
    }
}
