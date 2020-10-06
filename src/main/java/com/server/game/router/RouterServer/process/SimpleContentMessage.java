package com.server.game.router.RouterServer.process;

import com.server.game.router.RouterServer.entity.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    Logger logger = LoggerFactory.getLogger(SimpleContentMessage.class);

    public SimpleContentMessage(String[] data, String sessionId){
        this.data = data;
        this.sessionId = sessionId;
    }

    @Override
    public String process() throws Exception {

        logger.info("Entering in method process");
        logger.info("execute SimpleContentMessage");
        logger.info("session Id "+ sessionId);

        //validation content data
        String origin = data[0];
        String lobbyCode = data[2];

        //validate origin
        if(!origin.equalsIgnoreCase("CLIENT")){
            logger.info("error invalid message origin");
            return "500:INVALIDORIGIN";
        }

        //validate lobby
        boolean validLobby = false;
        UserSession response = clientService.findBySession(sessionId);

        //validate session id
        if(response == null){
            logger.info("error session is invalid");
            return "500:INVALIDSESSION";
        }

        //validate lobby code
        if(response.getLobbyClient().equalsIgnoreCase(lobbyCode)) {
            validLobby = true;
        }

        if(!validLobby){
            logger.info("error lobby is invalid");
            return "500:INVALIDLOBBY";
        }

        return null;
    }
}
