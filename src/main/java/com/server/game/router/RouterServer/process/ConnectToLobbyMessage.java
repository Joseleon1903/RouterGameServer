package com.server.game.router.RouterServer.process;

import com.server.game.router.RouterServer.entity.UserSession;
import com.server.game.router.RouterServer.entity.Lobby;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**\
 *   ConnectToLobbyMessage connect client to Lobby
 *
 *
 *    0             1               2          3             4             5           6
 *    Origin | operation code| lobby type| lobby code|  playeraName| player Session| isHost
 *
 *   CLIENT&202LB&Private&QUEIO&mario01&jdhfsdfkdjjdssd88&0
 */
public class ConnectToLobbyMessage  extends FactoryMessage {


    public ConnectToLobbyMessage(String[] data, String sessionId){
        this.data = data;
        this.sessionId = sessionId;
    }

    @Override
    public String process() throws Exception {

        //validate exiting lobby
        String lobbyCode = data[3];

        boolean isHost = (Integer.parseInt(data[6]) == 1) ? true : false;
        boolean validLobby = false;

        Lobby refLobby= null;

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "http://localhost:8080/lobbys";
        ResponseEntity<Lobby[]> response = restTemplate.getForEntity(fooResourceUrl , Lobby[].class);

        for (Lobby lb: response.getBody()) {
            if(lb.getLobbyCode().equalsIgnoreCase(lobbyCode)) {
                validLobby = true;
                refLobby = lb;
            }
        }

        if(!validLobby){
            return "ERROR|202LB|BADLOBBYCODE";
        }

        UserSession cl = new UserSession();
        cl.setSessionId(sessionId);
        cl.setPlayerName(data[4]);
        cl.setIsHost(isHost);
        cl.setLobbyClient(lobbyCode);

        //validation lobby can add more player
        if(refLobby.getPlayerCount() == refLobby.getCapacity()){
            return "ERROR|202LB|FULLLOBBYCODE";
        }

        int playerinLobbby = refLobby.getPlayerCount();
        playerinLobbby++;
        refLobby.setPlayerCount(playerinLobbby);

        //update lobby count

        HttpEntity<Lobby> requestL = new HttpEntity<>(refLobby);
        String ResourceUrlLobby
                = "http://localhost:8080/create/lobby";

        restTemplate.postForObject(ResourceUrlLobby, requestL, Lobby.class);

        HttpEntity<UserSession> request = new HttpEntity<>(cl);
        String resourceUrl= "http://localhost:8080/register/client";

        UserSession response2 = restTemplate.postForObject(resourceUrl, request, UserSession.class);

        //add client to lobby count

        if(response2 != null){
            return "SERVER|202LB|OK";
        }

        return null;
    }
}
