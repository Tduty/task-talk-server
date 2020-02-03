package info.tduty.typetalkserver.data.event;

import com.google.gson.annotations.SerializedName;

public class Event {

    @SerializedName("type")
    private String type;

    @SerializedName("payload")
    private EventPayload eventPayload;

    public Event(String type, EventPayload eventPayload) {
        this.type = type;
        this.eventPayload = eventPayload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EventPayload getEventPayload() {
        return eventPayload;
    }

    public void setEventPayload(EventPayload eventPayload) {
        this.eventPayload = eventPayload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (type != null ? !type.equals(event.type) : event.type != null) return false;
        return eventPayload != null ? eventPayload.equals(event.eventPayload) : event.eventPayload == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (eventPayload != null ? eventPayload.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Event{" +
                "type='" + type + '\'' +
                ", eventPayload=" + eventPayload +
                '}';
    }
}
