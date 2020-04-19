package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.entity.UserEntity;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.data.event.EventPayload;
import info.tduty.typetalkserver.data.event.payload.UserStatusPayload;
import info.tduty.typetalkserver.repository.wrapper.UserWrapper;
import info.tduty.typetalkserver.service.ws.EventSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ConnectionHandler {

    private final static String CONNECTED_STATUS = "connected";
    private final static String DISCONNECTED_STATUS = "disconnected";

    private UserWrapper userWrapper;
    private EventSender eventSender;

    @Autowired
    public ConnectionHandler(UserWrapper userWrapper, EventSender eventSender) {
        this.userWrapper = userWrapper;
        this.eventSender = eventSender;
    }

    public void afterConnect(String userId) {
        sendChangeStateEvent(userId, CONNECTED_STATUS);
    }

    public void beforeDisconnected(String userId) {
        sendChangeStateEvent(userId, DISCONNECTED_STATUS);
    }

    private void sendChangeStateEvent(String userId, String state) {
        Optional<UserEntity> user = userWrapper.get(userId);
        if (!user.isPresent() || user.get().getTeacher()) return;
        UserEntity teacher = user.get().getClassEntity().getTeacher();
        eventSender.send(teacher.getId(), mapToEvent(userId, state));
    }

    private Event mapToEvent(String userId, String state) {
        return new Event(
                EventPayload.Type.USER_STATUS.getString(),
                new UserStatusPayload(userId, state)
        );
    }
}
