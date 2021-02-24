package com.server.game.router.RouterServer.process;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.entity.UserProfile;
import com.server.game.router.RouterServer.entity.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

/**\
 *   ConnectToLobbyMessage connect client to Lobby
 *
 *
 *    0             1               2          3             4           5           6                  7            8            9
 *    Origin | operation code| lobby type| lobby code| playerCode | playeraName| playertId | playernationality |player Session| isHost
 *
 *   CLIENT&202LB&Private&QUEIO&mario01&jdhfsdfkdjjdssd88&0
 */
public class ConnectToLobbyMessage extends FactoryMessage {

    Logger logger = LoggerFactory.getLogger(ConnectToLobbyMessage.class);

    public ConnectToLobbyMessage(String[] data, String sessionId){
        this.data = data;
        this.sessionId = sessionId;
    }

    @Override
    public String process() throws Exception {

        logger.info("Entering in method process");
        logger.info("execute ConnectToLobbyMessage");
        logger.info("session Id "+ sessionId);

        //validate exiting lobby
        String lobbyCode = data[3];
        logger.info("lobby to connect: "+ lobbyCode);

        boolean isHost = (Integer.parseInt(data[9]) == 1) ? true : false;
        boolean validLobby = false;

        Lobby refLobby= null;

        List<Lobby> response = lobbyService.avaliableLobby();

        for (Lobby lb: response) {
            if(lb.getLobbyCode().equalsIgnoreCase(lobbyCode)) {
                validLobby = true;
                refLobby = lb;
            }
        }

        if(!validLobby){
            logger.info("lobby is invalid code");
            return "ERROR|202LB|BADLOBBYCODE";
        }

        UserSession cl = new UserSession();
        cl.setSessionId(sessionId);
        cl.setPlayerName(data[5]);
        cl.setIsHost(isHost);
        cl.setLobbyClient(lobbyCode);
        cl.setPlayerId(data[6]);
        cl.setNationality(data[7]);
        cl.setPlayerCode(data[4]);

        //validation lobby can add more player
        if(refLobby.getPlayerCount() == refLobby.getCapacity()){
            logger.info("lobby is full..");
            return "ERROR|202LB|FULLLOBBYCODE";
        }

        int playerinLobbby = refLobby.getPlayerCount();
        playerinLobbby++;
        refLobby.setPlayerCount(playerinLobbby);

        //update lobby count
        lobbyService.createGameLobby(refLobby);

        //add client to lobby count
        UserSession response2 = clientService.registerClient(cl);



        //if lobby is full after add new play start Game
        if(refLobby.getPlayerCount() == refLobby.getCapacity()){

            //prepare information for player one
            UserSession playerOne = clientService.findByPlayerCode("PL1", lobbyCode);

            UserProfile profile1 = userProfileService.getProfile(playerOne.getPlayerId());

            if(profile1 == null){
                return "ERROR|202LB|BADLOBBYCODE";
            }

            String playerOnePic = profile1.getProfilePicture();

            String playerOneInfo = "|PL1|"+playerOne.getPlayerName()+"|"+
                                     playerOne.getPlayerId()+"|"+  playerOne.getNationality()+"|"+playerOnePic;

            //prepare information for player two
            UserSession playerTwo = clientService.findByPlayerCode("PL2", lobbyCode);

            UserProfile profile2 = userProfileService.getProfile(playerTwo.getPlayerId());

            if(profile2 == null){
                return "ERROR|202LB|BADLOBBYCODE";
            }

            String playerTwoPic = profile2.getProfilePicture();

            String playerTwoInfo = "|PL2|"+playerTwo.getPlayerName()+"|"+
                    playerTwo.getPlayerId()+"|"+  playerTwo.getNationality()+"|"+playerTwoPic;

            return "SERVER|202LB|LOBBYREADY"+ playerOneInfo+playerTwoInfo;
        }

        if(response2 != null){
            logger.info("successful connect to lobby");
            return "SERVER|202LB|OK";
        }
        return null;
    }
}
