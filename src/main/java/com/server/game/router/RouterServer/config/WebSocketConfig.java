package com.server.game.router.RouterServer.config;

/**
 * Created by jose eduardo on 9/29/2020.
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private ServerCheckersWebSocketHandler serverCheckersWebSocketHandler;

    @Autowired
    private ServerChessWebSocketHandler serverChessWebSocketHandler;


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {

        // handler checker lobby
        webSocketHandlerRegistry.addHandler(serverCheckersWebSocketHandler, "/checker/lobby-manager").setAllowedOrigins("*");

        // handler chess lobby
        webSocketHandlerRegistry.addHandler(serverChessWebSocketHandler, "/chess/lobby-manager").setAllowedOrigins("*");
    }

}
