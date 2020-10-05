package com.server.game.router.RouterServer.repository;

import com.server.game.router.RouterServer.entity.Lobby;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jose eduardo on 9/30/2020.
 */
public interface LobbyRepository extends CrudRepository<Lobby, Long>{

    Lobby findByLobbyCode(String lobby);

}
