package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

public class ChatDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("icon")
    private String icon;

    @SerializedName("type")
    private String type;

    @SerializedName("description")
    private String description;

    public ChatDTO(String id, String title, String icon, String type, String description) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.type = type;
        this.description = description;
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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChatDTO chatDTO = (ChatDTO) o;

        if (id != null ? !id.equals(chatDTO.id) : chatDTO.id != null) return false;
        if (title != null ? !title.equals(chatDTO.title) : chatDTO.title != null) return false;
        if (icon != null ? !icon.equals(chatDTO.icon) : chatDTO.icon != null) return false;
        if (type != null ? !type.equals(chatDTO.type) : chatDTO.type != null) return false;
        return description != null ? description.equals(chatDTO.description) : chatDTO.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ChatDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
