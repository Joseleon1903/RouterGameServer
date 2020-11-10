package com.server.game.router.RouterServer.process;

import com.server.game.router.RouterServer.service.ClientService;
import com.server.game.router.RouterServer.service.LobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by jose eduardo on 9/30/2020.
 */
public abstract class FactoryMessage {

    protected String[] data;

    protected String sessionId;

    protected ClientService clientService;

    protected LobbyService lobbyService;

    public static FactoryMessage getMessage(String[] data, String sessionId){

        //message 201LB create a lobby
        if(data[0].equalsIgnoreCase("SERVER")  && data[1].equalsIgnoreCase("201LB")){
            return new CreateLobbyFactoryMessage(data, sessionId);
        }
        if(data[0].equalsIgnoreCase("SERVER")  && data[1].equalsIgnoreCase("202LB")){
            return new StartGameMessage(data, sessionId);
        }

        if(data[0].equalsIgnoreCase("CLIENT")  && data[1].equalsIgnoreCase("202LB")){
            return new ConnectToLobbyMessage(data, sessionId);
        }

        if(data[0].equalsIgnoreCase("CLIENT")  && data[1].equalsIgnoreCase("300LB")){
            return new RematchGameMessage(data, sessionId);
        }

        if(data[0].equalsIgnoreCase("CLIENT") || data[0].equalsIgnoreCase("SERVER")
            && data[1].equalsIgnoreCase("102LB")){
            return new SimpleContentMessage(data, sessionId);
        }
        return null;
    }

    public abstract String process() throws Exception;

    public void supplyService(ClientService clientService , LobbyService lobbyService){
        this.lobbyService = lobbyService;
        this.clientService = clientService;
    }
}
