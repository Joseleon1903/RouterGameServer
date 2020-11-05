package com.server.game.router.RouterServer.controller;

import com.server.game.router.RouterServer.entity.Lobby;
import com.server.game.router.RouterServer.service.LobbyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jose eduardo on 11/3/2020.
 */
@RestController
public class PublicLobbyCheckerRestController {

    Logger logger = LoggerFactory.getLogger(LobbyRestController.class);

    @Autowired
    private LobbyService lobbyService;

    @GetMapping("/checker/lobby")
    ResponseEntity<List<Lobby>> getServerPublicLobby(@RequestParam("count") int count) {
        List<Lobby> publicLobbyList = lobbyService.getPublicCheckersLobbyAvaliabe(count);
        return ResponseEntity.ok(publicLobbyList);
    }

    @GetMapping("/checker/lobby/testdata")
    ResponseEntity setServerPublicLobbyTestData() {
       lobbyService.setPublicCheckersLobbyTestData();
       return ResponseEntity.ok("OK");
    }

    @PostMapping("/checker/lobby/testdata")
    ResponseEntity setServerPublicLobbyTestData(@RequestBody Lobby lobby) {
        if(lobby != null){
            lobbyService.createGameLobby(lobby);
        }
        return ResponseEntity.ok("OK");
    }

}
