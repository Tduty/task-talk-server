package info.tduty.typetalkserver.domain.handler;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.event.EventPayload;

public interface EventHandler<T extends EventPayload> {

    void handle(User user, T payload);
}
