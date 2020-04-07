package info.tduty.typetalkserver.configuration;

import info.tduty.typetalkserver.data.User;
import info.tduty.typetalkserver.service.ws.SocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private SocketHandler socketHandler;

    @Autowired
    public WebSocketConfiguration(SocketHandler socketHandler) {
        this.socketHandler = socketHandler;
    }

    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(socketHandler, "/ws").setAllowedOrigins("*")
                .setHandshakeHandler(new DefaultHandshakeHandler() {
                    @Override
                    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                        String uuid = request.getHeaders().get("id").get(0);
                        String name = request.getHeaders().get("name").get(0);
                        return new User(uuid, name);
                    }
                });
    }
}
