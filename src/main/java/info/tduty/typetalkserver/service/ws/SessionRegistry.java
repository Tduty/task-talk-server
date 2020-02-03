package info.tduty.typetalkserver.service.ws;

import info.tduty.typetalkserver.data.User;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionRegistry {

    private Map<User, WebSocketSession> sessionMap;

    public SessionRegistry() {
        sessionMap = new HashMap<>();
    }

    public void put(User user, WebSocketSession session) {
        sessionMap.put(user, session);
    }

    public void remove(User user) {
        sessionMap.remove(user);
    }

    public WebSocketSession get(User user) {
        return sessionMap.get(user);
    }
}
