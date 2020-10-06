package com.server.game.router.RouterServer.controller;

import com.server.game.router.RouterServer.entity.UserSession;
import com.server.game.router.RouterServer.service.ClientService;
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
public class ClientRestController {

    Logger logger = LoggerFactory.getLogger(ClientRestController.class);

    @Autowired
    private ClientService clientService;

    @PostMapping("/register/client")
    ResponseEntity<UserSession> createGameLobby(@RequestBody UserSession userSession) {
        UserSession userSessionOut = clientService.registerClient(userSession);
        return ResponseEntity.ok(userSessionOut);
    }

    @GetMapping("/clients")
    ResponseEntity<List<UserSession>> getServerClients() {
        return ResponseEntity.ok(clientService.serverClients());
    }

    @GetMapping("/client")
    ResponseEntity<UserSession> getClientSession(@RequestParam("sessionId") String sessionId) {
        UserSession found = clientService.findBySession(sessionId);
        return ResponseEntity.ok(found);
    }

    @DeleteMapping("/delete/client")
    ResponseEntity deleteClientSession(@RequestParam("sessionId") String sessionId) {
        clientService.deleteClient(sessionId);
        return ResponseEntity.ok().build();
    }
}
