package com.server.game.router.RouterServer.repository;

import com.server.game.router.RouterServer.entity.Lobby;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jose eduardo on 9/30/2020.
 */
public interface LobbyRepository extends CrudRepository<Lobby, Long>{

    Lobby findByLobbyCode(String lobby);

    @Query(value = "select * From Lobby where type = 'Public' and player_count = 1 and game_lobby =?2 and status = 'Online' ORDER BY id DESC limit ?1 ;"
    , nativeQuery = true)
    List<Lobby> findCheckersLobbysAvalible(int counter, String type);
}
