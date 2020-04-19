package info.tduty.typetalkserver.data.event;

public interface EventPayload {

    enum Type {

        USER_STATUS,
        MESSAGE_NEW,
        LESSON,
        TYPING;

        public String getString() {
            return name().toLowerCase();
        }

        public static Type to(String type) {
            return valueOf(type.toUpperCase());
        }
    }
}
