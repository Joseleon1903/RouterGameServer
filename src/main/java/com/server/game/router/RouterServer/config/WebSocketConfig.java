package com.server.game.router.RouterServer.config;

/**
 * Created by jose eduardo on 9/29/2020.
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
//
// ws://localhost:8080/web-socket
//
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(new ServerWebSocketHandler(), "/lobby-Manager").setAllowedOrigins("*");
    }

}
