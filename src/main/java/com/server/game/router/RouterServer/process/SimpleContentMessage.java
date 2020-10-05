package com.server.game.router.RouterServer.process;

import com.server.game.router.RouterServer.entity.UserSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**\
 *   SimpleContentMessage common message from a player lobby
 *
 *   content:
 *
 *    0             1               2         3           4        continue
 *    Origin | operation code| lobby code|  data   |   .....
 *
 *   CLIENT&102LB&QUEIO&data
 */
public class SimpleContentMessage extends FactoryMessage {

    public SimpleContentMessage(String[] data, String sessionId){
        this.data = data;
        this.sessionId = sessionId;
    }

    @Override
    public String process() throws Exception {

        //validation content data
        String origin = data[0];
        String lobbyCode = data[2];

        //validate origin
        if(!origin.equalsIgnoreCase("CLIENT")){
            return "500:INVALIDORIGIN";
        }

        //validate lobby
        boolean validLobby = false;

        RestTemplate restTemplate = new RestTemplate();
        String getClientResourceUrl = "http://localhost:8080/client?sessionId="+ sessionId;
        ResponseEntity<UserSession> response = restTemplate.getForEntity(getClientResourceUrl , UserSession.class);

        //validate session id
        if(response.getBody() == null){
            return "500:INVALIDSESSION";
        }

        //validate lobby code
        if(response.getBody().getLobbyClient().equalsIgnoreCase(lobbyCode)) {
            validLobby = true;
        }

        if(!validLobby){
            return "500:INVALIDLOBBY";
        }

        return null;
    }
}
