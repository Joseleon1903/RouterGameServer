package com.server.game.router.RouterServer.service;

import com.server.game.router.RouterServer.entity.UserSession;
import com.server.game.router.RouterServer.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

/**
 * Created by jose eduardo on 10/1/2020.
 */
@Service
public class ConnectionServiceImpl implements ConnectionService {

    Logger logger = LoggerFactory.getLogger(ConnectionServiceImpl.class);

    @Autowired
    private ClientService clientService;

    @Autowired
    private LobbyService lobbyService;

    @Override
    public void NotifySessionConnection(WebSocketSession session) {

        logger.info("Entering in NotifySessionConnection");
        String sessionId = session.getId();
        logger.info("Session id: "+ sessionId);

        UserSession userSession = new UserSession();
        userSession.setPlayerName(UserType.ANONIMOUS.getType());
        userSession.setSessionId(sessionId);
        userSession.setIsHost(false);
        userSession.setLobbyClient("");

        clientService.registerClient(userSession);
    }

    @Override
    public UserSession NotifySessionDisconnection(WebSocketSession session) {
        logger.info("Entering in NotifySessionDisconnection");

        String sessionId = session.getId();

        UserSession response = clientService.findBySession(sessionId);

        logger.info("Session id: "+ sessionId);
        clientService.deleteClient(sessionId);

        return response;
    }

    @Override
    public void NotifyLobbyClose(String lobbyCode) {

        logger.info("Entering in NotifyLobbyClose");
        logger.info("lobby code: "+lobbyCode );

        lobbyService.deleteLobby(lobbyCode);

        logger.info("Exiting in NotifyLobbyClose");

    }
}
