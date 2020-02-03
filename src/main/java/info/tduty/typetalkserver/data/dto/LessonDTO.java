package info.tduty.typetalkserver.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LessonDTO {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("icon")
    private String icon;

    @SerializedName("status")
    private int status;

    @SerializedName("description")
    private String description;

    @SerializedName("expected")
    private List<ExpectedDTO> expected;

    @SerializedName("tasks")
    private List<TaskDTO> taskDTOList;

    public LessonDTO(String id,
                     String title,
                     String icon,
                     int status,
                     String description,
                     List<ExpectedDTO> expected,
                     List<TaskDTO> taskDTOList) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.status = status;
        this.description = description;
        this.expected = expected;
        this.taskDTOList = taskDTOList;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ExpectedDTO> getExpected() {
        return expected;
    }

    public void setExpected(List<ExpectedDTO> expected) {
        this.expected = expected;
    }

    public List<TaskDTO> getTaskDTOList() {
        return taskDTOList;
    }

    public void setTaskDTOList(List<TaskDTO> taskDTOList) {
        this.taskDTOList = taskDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonDTO lessonDTO = (LessonDTO) o;

        if (status != lessonDTO.status) return false;
        if (id != null ? !id.equals(lessonDTO.id) : lessonDTO.id != null) return false;
        if (title != null ? !title.equals(lessonDTO.title) : lessonDTO.title != null) return false;
        if (icon != null ? !icon.equals(lessonDTO.icon) : lessonDTO.icon != null) return false;
        if (description != null ? !description.equals(lessonDTO.description) : lessonDTO.description != null)
            return false;
        if (expected != null ? !expected.equals(lessonDTO.expected) : lessonDTO.expected != null) return false;
        return taskDTOList != null ? taskDTOList.equals(lessonDTO.taskDTOList) : lessonDTO.taskDTOList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (expected != null ? expected.hashCode() : 0);
        result = 31 * result + (taskDTOList != null ? taskDTOList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LessonDTO{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", expected=" + expected +
                ", taskDTOList=" + taskDTOList +
                '}';
    }

    public class ExpectedDTO {

        @SerializedName("icon")
        private String icon;

        @SerializedName("title")
        private String title;

        public ExpectedDTO(String icon, String title) {
            this.icon = icon;
            this.title = title;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LessonDTO.ExpectedDTO that = (LessonDTO.ExpectedDTO) o;

            if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
            return title != null ? title.equals(that.title) : that.title == null;
        }

        @Override
        public int hashCode() {
            int result = icon != null ? icon.hashCode() : 0;
            result = 31 * result + (title != null ? title.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "ExpectedPayload{" +
                    "icon='" + icon + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
