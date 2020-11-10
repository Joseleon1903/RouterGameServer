package com.server.game.router.RouterServer.config;
/**
 * Created by jose eduardo on 9/29/2020.
 */

import com.server.game.router.RouterServer.process.*;
import com.server.game.router.RouterServer.service.ClientService;
import com.server.game.router.RouterServer.service.ConnectionService;
import com.server.game.router.RouterServer.service.LobbyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Component
public class ServerCheckersWebSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerCheckersWebSocketHandler.class);

    private final HashMap< String, Set<WebSocketSession>> lobbySessionListener = new HashMap<>();

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private LobbyService lobbyService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        LOGGER.info("Someone has connect to the server....");

        connectionService.NotifySessionConnection(session);

        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.info("Someone has disconnect from the server....");
        String lobby = connectionService.NotifySessionDisconnection(session);
        if(lobby != null){
            lobbySessionListener.get(lobby).remove(session);
        }

        if(lobbySessionListener.get(lobby) != null && lobbySessionListener.get(lobby).size() > 0){
            for (WebSocketSession ses: lobbySessionListener.get(lobby)) {
                String disconectMessage= "SERVER|102LB|CONNECTIONLOST";
                TextMessage messageOut = new TextMessage(disconectMessage);
                ses.sendMessage(messageOut);
            }
        }else if (lobbySessionListener.get(lobby) != null && lobbySessionListener.get(lobby).size() == 0){
            lobbySessionListener.remove(lobby);
        }
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);

        //identifier client  {Server - UserSession}
        String[] data = message.getPayload().split("&");

        if(data.length < 2){
            TextMessage responeError = new TextMessage("500");
            session.sendMessage(responeError);
            return;
        }

        FactoryMessage msg = FactoryMessage.getMessage(data, session.getId());
        msg.supplyService(clientService, lobbyService);
        String response = (msg != null) ?msg.process() : null;

        LOGGER.info("Response fro process: "+response);

        if(response != null && msg instanceof CreateLobbyFactoryMessage){

            Set<WebSocketSession> sesData = new HashSet<>();
            lobbySessionListener.put(data[3], sesData);
            TextMessage respone = new TextMessage(response);
            session.sendMessage(respone);

        }else if(response != null && msg instanceof ConnectToLobbyMessage){

            if(lobbySessionListener.get(data[3]) != null) {
                lobbySessionListener.get(data[3]).add(session);
            }

            Set<WebSocketSession> lobbysessions = lobbySessionListener.get(data[3]);
            lobbysessions.forEach(webSocketSession -> {
                try {
                    TextMessage messageOut = new TextMessage(response);
                    webSocketSession.sendMessage(messageOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }else if(response != null && msg instanceof StartGameMessage){

            Set<WebSocketSession> lobbysessions = lobbySessionListener.get(data[3]);
            lobbysessions.forEach(webSocketSession -> {
                try {
                    TextMessage messageOut = new TextMessage(response);
                    webSocketSession.sendMessage(messageOut);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else if(msg instanceof RematchGameMessage){

            Set<WebSocketSession> lobbysessions = lobbySessionListener.get(data[2]);

            lobbysessions.forEach(webSocketSession -> {
                try {
                    TextMessage messageOut = new TextMessage(response);
                    //for duplicate message omit current session
                    if(webSocketSession.getId() != session.getId()){
                        webSocketSession.sendMessage(messageOut);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }else if(msg instanceof SimpleContentMessage || msg instanceof RematchGameMessage){
            Set<WebSocketSession> lobbysessions = lobbySessionListener.get(data[2]);

            lobbysessions.forEach(webSocketSession -> {
                try {
                    //for duplicate message omit current session
                    if(webSocketSession.getId() != session.getId()){
                        webSocketSession.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}