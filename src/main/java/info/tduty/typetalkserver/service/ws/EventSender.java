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

    public void send(User user, Event event) {
        try {
            String jsonStr = gson.toJson(event);
            WebSocketSession session = sessionRegistry.get(user);
            synchronized(session) {
                session.sendMessage(new TextMessage(jsonStr));
            }
            logger.debug("message sent to name: {}, id: {}, type: {}, event: {}", user.getName(), user.getId(), event.getType(), event);
        } catch (IOException e) {
            logger.error("message not sent to name: {}, id: {}, event: {}", user.getName(), user.getId(), event);
        }
    }

}
