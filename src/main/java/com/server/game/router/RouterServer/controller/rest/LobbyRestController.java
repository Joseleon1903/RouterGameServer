package com.server.game.router.RouterServer.controller.rest;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.service.LobbyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Created by jose eduardo on 9/30/2020.
 */
@RestController
public class LobbyRestController {

    Logger logger = LoggerFactory.getLogger(LobbyRestController.class);

    @Autowired
    private LobbyService lobbyService;

    @PostMapping("/create/lobby")
    ResponseEntity<Lobby> createGameLobby(@RequestBody Lobby lobby){
        Lobby lob = lobbyService.createGameLobby(lobby);
        return ResponseEntity.ok(lob);
    }

    @GetMapping("/lobbys")
    ResponseEntity<List<Lobby>> getServerLobby() {
        return ResponseEntity.ok(lobbyService.avaliableLobby());
    }

    @GetMapping("/lobby")
    ResponseEntity<Lobby> getServerLobbyCode(@RequestParam("lobbyCode") String lobbyCode) {
        return ResponseEntity.ok(lobbyService.getLobbyCode(lobbyCode));
    }

    @DeleteMapping("/delete/lobby")
    ResponseEntity deleteLobby(@RequestParam("lobbyCode") String lobbyCode) {
        lobbyService.deleteLobby(lobbyCode);
        return ResponseEntity.ok().build();
    }
}
