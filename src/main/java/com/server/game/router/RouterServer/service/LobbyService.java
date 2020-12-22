package com.server.game.router.RouterServer.service;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.enums.GameLobbyType;
import com.server.game.router.RouterServer.enums.LobbyType;

import java.util.List;

/**
 * Created by jose eduardo on 9/30/2020.
 */
public interface LobbyService {

    Lobby createGameLobby(Lobby lobby);

    List<Lobby> avaliableLobby();

    void deleteLobby(String lobbyCode);

    Lobby getLobbyCode(String lobbyCode);

    List<Lobby> getPublicCheckersLobbyAvaliabe(int count, String type);

    void setPublicCheckersLobbyTestData(String type);
}
