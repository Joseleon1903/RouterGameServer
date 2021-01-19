package com.server.game.router.RouterServer.process;

import com.server.game.router.RouterServer.entity.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**\
 *   ChessCheckMateMessage notify opponet game win
 *
 *
 *   content:
 *
 *    0             1               2         3              3              4             5
 *    Origin | operation code| lobby code| game type |  piece type   |  piecePosX  |  piecePosY
 *
 *    CLIENT&102PL&QUEIO&CHESS&QUEEEN&0&7
 */
public class ChessPromotePieceMessage extends FactoryMessage{

    Logger logger = LoggerFactory.getLogger(ChessPromotePieceMessage.class);

    public ChessPromotePieceMessage(String[] data, String sessionId){
        this.data = data;
        this.sessionId = sessionId;
    }


    @Override
    public String process() throws Exception {
        logger.info("Entering in method process");
        logger.info("execute ChessPromotePieceMessage");
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
