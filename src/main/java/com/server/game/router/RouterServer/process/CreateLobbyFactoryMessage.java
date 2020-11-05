package com.server.game.router.RouterServer.process;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.entity.UserSession;
import com.server.game.router.RouterServer.enums.GameLobbyType;
import com.server.game.router.RouterServer.enums.UserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**\
 *   FactoryMessage Detail for create a lobby
 *
 *   content:
 *
 *    0             1               2          3        4             5                6            7           8             9
 *    Origin | operation code| lobby type| lobby code|lobby Map| lobby time|  lobby capacity| gametype lobby Identifier| lobby status
 *
 *   SERVER&201LB&Private&QUEIO&city&day&2&CHECKER&jdjd:ksjd:kdjd:90&Online
 */
public class CreateLobbyFactoryMessage extends FactoryMessage {

    Logger logger = LoggerFactory.getLogger(CreateLobbyFactoryMessage.class);

    public CreateLobbyFactoryMessage(String[] data, String sessionId){
        this.data = data;
        this.sessionId = sessionId;
    }

    @Override
    public String process() throws Exception {

        logger.info("Entering in method process");
        logger.info("execute CreateLobbyFactoryMessage");
        logger.info("session Id "+ sessionId);

        //validate is lobby alredy exist
        Boolean validLobby = false;

        List<Lobby> response = lobbyService.avaliableLobby();
        for (Lobby lb: response) {
            if(lb.getLobbyCode().equalsIgnoreCase(data[3])) {
                validLobby = true;
            }
        }

        if(validLobby){
            logger.info("Error lobby alredy exist");
            return "ERROR|201LB|ALREADYLOBBY";
        }

        //creation of a new game lobby
        int capacity = Integer.parseInt(data[6]);
        int playerCount =  0;

        Lobby lb = new Lobby();
        lb.setLobbyMap(data[4]);
        lb.setLobbyTime(data[5]);
        lb.setLobbyCode(data[3]);
        lb.setCapacity(capacity);
        lb.setPlayerCount(playerCount);
        lb.setStatus(data[9]);
        lb.setType(data[2]);
        lb.setSessionIdentifier(sessionId);
        lb.setGameLobby(data[7]);

        lb.setGameLobby(GameLobbyType.CHECKER);

        lobbyService.createGameLobby(lb);

        //update connection host of the server
        UserSession userHost = clientService.findBySession(sessionId);

        userHost.setPlayerName(UserType.LOBBYHOST.getType());
        userHost.setIsHost(true);
        userHost.setLobbyClient(data[3]);

        clientService.registerClient(userHost);
        logger.info("lobby successful create");
        return "CLIENT|201LB|OK";
    }
}
