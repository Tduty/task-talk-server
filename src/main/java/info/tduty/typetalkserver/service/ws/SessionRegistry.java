package info.tduty.typetalkserver.service.ws;

import info.tduty.typetalkserver.data.User;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionRegistry {

    private Map<String, WebSocketSession> sessionMap;

    public SessionRegistry() {
        sessionMap = new HashMap<>();
    }

    public void put(String userId, WebSocketSession session) {
        sessionMap.put(userId, session);
    }

    public void remove(String userId) {
        sessionMap.remove(userId);
    }

    public WebSocketSession get(String userId) {
        return sessionMap.get(userId);
    }
}
