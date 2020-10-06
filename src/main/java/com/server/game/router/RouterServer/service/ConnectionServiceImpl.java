package com.server.game.router.RouterServer.service;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.entity.UserSession;
import com.server.game.router.RouterServer.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
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

    private RestTemplate restTemplate= new RestTemplate();

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
    public String NotifySessionDisconnection(WebSocketSession session) {
        logger.info("Entering in NotifySessionDisconnection");

        String sessionId = session.getId();
        String lobbyCode = null;

        UserSession response = clientService.findBySession(sessionId);
        if(response != null && !response.getLobbyClient().isEmpty()){
            lobbyCode = response.getLobbyClient();
        }

        logger.info("Session id: "+ sessionId);
        clientService.deleteClient(sessionId);
        logger.info("validating if session host a lobby");

        if(response.getIsHost() && !response.getLobbyClient().isEmpty()){
            logger.info("UserSession is a host and has a lobby");
            logger.info("lobby code "+ lobbyCode);
            lobbyService.deleteLobby(lobbyCode);
        }else{
            Lobby responseLobby = lobbyService.getLobbyCode(lobbyCode);
            if(responseLobby != null){
                int playerL = responseLobby.getPlayerCount();
                playerL--;
                responseLobby.setPlayerCount(playerL);
                lobbyService.createGameLobby(responseLobby);
            }
        }

        return lobbyCode;
    }
}
