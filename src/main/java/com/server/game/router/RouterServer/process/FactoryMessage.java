package com.server.game.router.RouterServer.process;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by jose eduardo on 9/30/2020.
 */
public abstract class FactoryMessage {

    protected String[] data;

    protected String sessionId;

    public static FactoryMessage getMessage(String[] data, String sessionId){

        //message 201LB create a lobby
        if(data[0].equalsIgnoreCase("Server")  && data[1].equalsIgnoreCase("201LB")){
            return new CreateLobbyFactoryMessage(data, sessionId);
        }
        if(data[0].equalsIgnoreCase("CLIENT")  && data[1].equalsIgnoreCase("202LB")){
            return new ConnectToLobbyMessage(data, sessionId);
        }

        if(data[0].equalsIgnoreCase("CLIENT") || data[0].equalsIgnoreCase("SERVER")
            && data[1].equalsIgnoreCase("102LB")){
            return new SimpleContentMessage(data, sessionId);
        }
        return null;
    }

    public abstract String process() throws Exception;
}
