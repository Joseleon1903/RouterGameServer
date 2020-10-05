package com.server.game.router.RouterServer.service;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.entity.UserSession;
import com.server.game.router.RouterServer.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        HttpEntity<UserSession> request = new HttpEntity<>(userSession);
        String resourceUrl= "http://localhost:8080/register/client";
        restTemplate.postForObject(resourceUrl, request, UserSession.class);

    }

    @Override
    public String NotifySessionDisconnection(WebSocketSession session) {
        logger.info("Entering in NotifySessionDisconnection");

        String sessionId = session.getId();
        String lobbyCode = null;
        String getResourceUrl= "http://localhost:8080/client?sessionId="+sessionId;

        ResponseEntity<UserSession> response = restTemplate.getForEntity(getResourceUrl , UserSession.class);

        if(response.getBody() != null && !response.getBody().getLobbyClient().isEmpty()){
            lobbyCode = response.getBody().getLobbyClient();
        }

        logger.info("Session id: "+ sessionId);
        String resourceUrl= "http://localhost:8080/delete/client?sessionId="+sessionId;
        restTemplate.delete(resourceUrl);
        logger.info("validating if session host a lobby");

        if(response.getBody().getIsHost() && !response.getBody().getLobbyClient().isEmpty()){
            logger.info("UserSession is a host and has a lobby");
            logger.info("lobby code "+ lobbyCode);
            String deleteResourceUrl= "http://localhost:8080/delete/lobby?lobbyCode="+lobbyCode;
            restTemplate.delete(deleteResourceUrl);
        }else{

            restTemplate = new RestTemplate();
            String fooResourceUrl = "http://localhost:8080/lobby?lobbyCode="+lobbyCode;
            ResponseEntity<Lobby> responseLobby = restTemplate.getForEntity(fooResourceUrl , Lobby.class);

            int playerL = responseLobby.getBody().getPlayerCount();
            playerL--;
            responseLobby.getBody().setPlayerCount(playerL);

            String updateResourceUrl = "http://localhost:8080/create/lobby";
            HttpEntity<Lobby> requestL = new HttpEntity<>(responseLobby.getBody());
            restTemplate.postForEntity(updateResourceUrl , requestL, Lobby.class);

        }

        return lobbyCode;
    }
}
