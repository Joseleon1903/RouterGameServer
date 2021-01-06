package com.server.game.router.RouterServer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jose eduardo on 11/5/2020.
 */
@RestController
public class RouterServerVersionController {

    Logger logger = LoggerFactory.getLogger(RouterServerVersionController.class);

    @GetMapping("/router/server/version")
    ResponseEntity<String> getServerVersion() {
        logger.info("Entering in resources: /router/server/version".toUpperCase());
        logger.info("version:0.0.1".toUpperCase());
        return ResponseEntity.ok("version:0.0.2-stable");
    }

}
