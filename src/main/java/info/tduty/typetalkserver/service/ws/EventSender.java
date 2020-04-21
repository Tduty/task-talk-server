package info.tduty.typetalkserver.service.ws;

import com.google.gson.Gson;
import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

@Component
public class EventSender {

    private final static Logger logger = LoggerFactory.getLogger(EventSender.class);

    private Gson gson;
    private SessionRegistry sessionRegistry;

    @Autowired
    public EventSender(Gson gson, SessionRegistry sessionRegistry) {
        this.gson = gson;
        this.sessionRegistry = sessionRegistry;
    }

    public void send(List<String> userIds, Event event) {
        for (String userId : userIds) {
            send(userId, event);
        }
    }

    public void send(String userId, Event event) {
        try {
            String jsonStr = gson.toJson(event);
            WebSocketSession session = sessionRegistry.get(userId);
            if (session != null) {
                synchronized(session) {
                    session.sendMessage(new TextMessage(jsonStr));
                    logger.debug("message sent to id: {}, type: {}, event: {}", userId, event.getType(), event);
                }
            }
        } catch (IOException e) {
            logger.error("message not sent to id: {}, event: {}", userId, event);
        }
    }

}
