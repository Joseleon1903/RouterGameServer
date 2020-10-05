package com.server.game.router.RouterServer.service;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.repository.LobbyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose eduardo on 9/30/2020.
 */
@Service
public class LobbyServiceImpl implements LobbyService{

    Logger logger = LoggerFactory.getLogger(LobbyServiceImpl.class);

    @Autowired
    private LobbyRepository lobbyRepository;

    @Override
    public Lobby createGameLobby(Lobby lobby) {
        logger.info("entering in method  createGameLobby");
        logger.info("Param: "+ lobby);
        if(lobby.getId() != null && lobby.getId() == 0){
            lobby.setId(null);
        }
        return lobbyRepository.save(lobby);
    }

    @Override
    public List<Lobby> avaliableLobby() {
        logger.info("entering in method  avaliableLobby");
        List<Lobby> list = new ArrayList<>();
        logger.info("returning list count"+ list.size());
        lobbyRepository.findAll().forEach(ind ->{
            list.add(ind);
        });
        return list;
    }

    @Override
    public void deleteLobby(String lobbyCode) {
        logger.info("entering in method  deleteLobby");
        logger.info("Param lobbyCode: "+ lobbyCode);
        logger.info("search a lobby..");

        Lobby lobby = lobbyRepository.findByLobbyCode(lobbyCode);

        if(lobby != null){
            logger.info("lobby if found..");
            logger.info("delete a lobby "+ lobby);
            lobbyRepository.delete(lobby);
        }

    }

    @Override
    public Lobby getLobbyCode(String lobbyCode) {
        logger.info("entering in method  getLobbyCode");
        logger.info("Param lobbyCode: "+ lobbyCode);
        Lobby lob = lobbyRepository.findByLobbyCode(lobbyCode);
        return lob;
    }
}
