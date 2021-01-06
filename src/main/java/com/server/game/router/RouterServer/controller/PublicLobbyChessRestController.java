package com.server.game.router.RouterServer.controller;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.enums.GameLobbyType;
import com.server.game.router.RouterServer.service.LobbyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jose eduardo on 12/19/2020.
 */
@RestController
public class PublicLobbyChessRestController {

    Logger logger = LoggerFactory.getLogger(PublicLobbyChessRestController.class);

    @Autowired
    private LobbyService lobbyService;

    @GetMapping("/chess/lobby")
    ResponseEntity<List<Lobby>> getServerPublicLobby(@RequestParam("count") int count) {
        List<Lobby> publicLobbyList = lobbyService.getPublicCheckersLobbyAvaliabe(count, GameLobbyType.CHESS);
        return ResponseEntity.ok(publicLobbyList);
    }

    @GetMapping("/chess/lobby/testdata")
    ResponseEntity setServerPublicLobbyTestData() {
        lobbyService.setPublicCheckersLobbyTestData(GameLobbyType.CHESS);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("chess/lobby/testdata")
    ResponseEntity setServerPublicLobbyTestData(@RequestBody Lobby lobby) {
        if(lobby != null){
            lobbyService.createGameLobby(lobby);
        }
        return ResponseEntity.ok("OK");
    }


}
