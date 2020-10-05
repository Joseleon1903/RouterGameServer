package com.server.game.router.RouterServer.process;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.entity.UserSession;
import com.server.game.router.RouterServer.enums.UserType;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**\
 *   FactoryMessage Detail for create a lobby
 *
 *   content:
 *
 *    0             1               2          3             4             5                6
 *    Origin | operation code| lobby type| lobby code| lobby capacity| lobby Identifier| lobby status
 *
 *   SERVER&201LB&Private&QUEIO&2&jdjd:ksjd:kdjd:90&Online
 */
public class CreateLobbyFactoryMessage extends FactoryMessage {

    public CreateLobbyFactoryMessage(String[] data, String sessionId){
        this.data = data;
        this.sessionId = sessionId;
    }

    @Override
    public String process() throws Exception {

        //validate is lobby alredy exist
        Boolean validLobby = false;

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/lobbys";
        ResponseEntity<Lobby[]> response = restTemplate.getForEntity(fooResourceUrl , Lobby[].class);

        for (Lobby lb: response.getBody()) {
            if(lb.getLobbyCode().equalsIgnoreCase(data[3])) {
                validLobby = true;
            }
        }

        if(validLobby){
            return "ERROR|201LB|ALREADYLOBBY";
        }

        //creation of a new game lobby
        int capacity = Integer.parseInt(data[4]);
        int playerCount =  0;

        Lobby lb = new Lobby();
        lb.setLobbyCode(data[3]);
        lb.setCapacity(capacity);
        lb.setPlayerCount(playerCount);
        lb.setStatus(data[6]);
        lb.setType(data[2]);
        lb.setSessionIdentifier(sessionId);

        HttpEntity<Lobby> request = new HttpEntity<>(lb);
        String cResourceUrl
                = "http://localhost:8080/create/lobby";

        restTemplate.postForObject(cResourceUrl, request, Lobby.class);

        //update connection host of the server

        //UserSession sessionHost =
        String getResourceUser = "http://localhost:8080/client?sessionId="+sessionId;
        ResponseEntity<UserSession> getResponse = restTemplate.getForEntity(getResourceUser , UserSession.class);
        UserSession userHost = getResponse.getBody();
        userHost.setPlayerName(UserType.LOBBYHOST.getType());
        userHost.setIsHost(true);
        userHost.setLobbyClient(data[3]);

        String UpdateResourceUser = "http://localhost:8080/register/client";
        HttpEntity<UserSession> requestUpdate = new HttpEntity<>(userHost);

        restTemplate.postForObject(UpdateResourceUser, requestUpdate, UserSession.class);

        return "CLIENT|201LB|OK";
    }
}
