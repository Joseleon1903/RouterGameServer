package com.server.game.router.RouterServer.service;

import com.server.game.router.RouterServer.entity.UserSession;

import java.util.List;

/**
 * Created by jose eduardo on 9/30/2020.
 */
public interface ClientService {

    UserSession registerClient(UserSession userSession);

    UserSession findBySession(String sessionId);

    UserSession findByPlayerCode(String playerCode, String lobbyCode);

    List<UserSession> serverClients();

    void deleteClient(String sessionId);

}
