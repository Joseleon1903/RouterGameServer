package com.server.game.router.RouterServer.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jose de leon on 1/25/2021.
 */
@RestController
public class RouterVersion {

    Logger logger = LoggerFactory.getLogger(RouterVersion.class);

    @GetMapping("/application/version")
    ResponseEntity<String> getServerVersion() {
        String version = "Router-Game-Server-0.0.1-SNAPSHOT";
        return ResponseEntity.ok(version);
    }


}
