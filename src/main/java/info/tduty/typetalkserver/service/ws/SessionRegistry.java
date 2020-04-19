package info.tduty.typetalkserver.service.ws;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.Map;

@Component
public class SessionRegistry {

    public final static String DISCONNECTED_STATUS = "disconnected";
    public final static String CONNECTED_STATUS = "connected";

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

    public boolean contains(String userId) {
        return sessionMap.containsKey(userId);
    }

    public String getStatusForUser(String userId) {
        if (contains(userId)) {
            return CONNECTED_STATUS;
        } else {
            return DISCONNECTED_STATUS;
        }
    }
}
