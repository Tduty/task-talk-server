package info.tduty.typetalkserver.service.ws;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.data.event.Event;
import info.tduty.typetalkserver.domain.handler.EventHandler;
import info.tduty.typetalkserver.domain.handler.pull.EventHandlerPull;
import info.tduty.typetalkserver.service.parser.EventParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

    private final static Logger logger = LoggerFactory.getLogger(SocketHandler.class);

    private SessionRegistry sessionRegistry;
    private EventParser eventParser;
    private EventHandlerPull eventHandlerPull;

    @Autowired
    public SocketHandler(SessionRegistry sessionRegistry,
                         EventParser eventParser,
                         EventHandlerPull eventHandlerPull) {
        this.sessionRegistry = sessionRegistry;
        this.eventParser = eventParser;
        this.eventHandlerPull = eventHandlerPull;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        User user = (User) session.getPrincipal();
        sessionRegistry.put(user.getId(), session);
        logger.debug("connection name: {}, id: {}", user.getName(), user.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
        try {
            User user = (User) session.getPrincipal();
            logger.debug("handle message, name: {}, id: {}, command: {}", user.getName(), user.getId(), message.getPayload());
            Event event = eventParser.parse(message.getPayload());
            EventHandler eventHandler = eventHandlerPull.getHandler(event.getType());
            eventHandler.handle(user, event.getEventPayload());
        } catch (Exception ex) {
            logger.error("Error handle message " + message.getPayload(), ex);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        User user = (User) session.getPrincipal();
        sessionRegistry.remove(user.getId());
        logger.debug("disconnect code: {}, reason: {}, name: {}, id: {}",
                status.getCode(), status.getReason(), user.getName(), user.getId());
    }
}
