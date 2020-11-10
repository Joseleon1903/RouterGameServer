package com.server.game.router.RouterServer.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**\
 *   StartGameMessage common message start lobby game
 *
 *   content:
 *
 *    0                  1            2          3           4          5          6
 *    destination | operation code| result | lobby code | game map | game time | gameType
 *
 *   {destination}&{operation}&{result}&{lobbyCode}&{map}&{time}&{gameType}
 */
public class StartGameMessage extends FactoryMessage {

    Logger logger = LoggerFactory.getLogger(StartGameMessage.class);

    public StartGameMessage(String[] data, String sessionId){
        this.data = data;
        this.sessionId = sessionId;
    }

    @Override
    public String process() throws Exception {

        logger.info("Entering in method process");
        logger.info("execute StartGameMessage");
        logger.info("session Id "+ sessionId);

        String destination  = data[0];
        String operation  = data[1];
        String result  = data[2];

        String lobbyCode = data[3];
        String map = data[4];
        String time = data[5];
        String gameType = data[6];

        logger.info("Parsing server message for clients ");

        String message = destination+"|"+operation+"|"+
                result+"|"+ lobbyCode+"|"+map+"|"+time+"|"+gameType;
        logger.info("Message: "+ message);
        return message;
    }
}
