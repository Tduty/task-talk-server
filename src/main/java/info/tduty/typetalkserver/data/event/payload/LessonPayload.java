package info.tduty.typetalkserver.data.event.payload;

import com.google.gson.annotations.SerializedName;
import info.tduty.typetalkserver.data.event.EventPayload;

import java.util.List;

public class LessonPayload implements EventPayload {

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
    private List<ExpectedPayload> expectedList;


    public LessonPayload(String id,
                         String title,
                         String icon,
                         int status,
                         String description,
                         List<ExpectedPayload> expectedList) {
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.status = status;
        this.description = description;
        this.expectedList = expectedList;
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

    public List<ExpectedPayload> getExpectedList() {
        return expectedList;
    }

    public void setExpectedList(List<ExpectedPayload> expectedList) {
        this.expectedList = expectedList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LessonPayload that = (LessonPayload) o;

        if (status != that.status) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        return expectedList != null ? expectedList.equals(that.expectedList) : that.expectedList == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + status;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (expectedList != null ? expectedList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LessonPayload{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", icon='" + icon + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", expectedList=" + expectedList +
                '}';
    }

    public class ExpectedPayload {

        @SerializedName("icon")
        private String icon;

        @SerializedName("title")
        private String title;

        public ExpectedPayload(String icon, String title) {
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

            ExpectedPayload that = (ExpectedPayload) o;

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
