package com.server.game.router.RouterServer.process;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**\
 *   RematchGameMessage common message player request play again
 *
 *   content:
 *
 *    0            1              2                 3
 *   Origin | operation code| lobby code|  rematch counter   |   .....
 */
public class RematchGameMessage extends FactoryMessage{

    Logger logger = LoggerFactory.getLogger(RematchGameMessage.class);

    public RematchGameMessage(String[] data, String sessionId){
        this.data = data;
        this.sessionId = sessionId;
    }

    @Override
    public String process() throws Exception {

        logger.info("Entering in method process");
        logger.info("execute RematchGameMessage");
        logger.info("session Id "+ sessionId);

        logger.info("player counter value: "+ data[3]);

        String Origin = data[0];

        String operation = data[1];
        String lobby = data[2];
        String rematchCounter = data[3];

        logger.info("Parsing message for clients ");

        String message = Origin+"|"+operation+"|"+lobby+"|"+ rematchCounter;
        logger.info("Message: "+ message);
        return message;
    }
}
