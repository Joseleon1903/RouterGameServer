package com.server.game.router.RouterServer.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jose eduardo on 11/5/2020.
 */
@Controller
public class RouterServerVersionController {

    Logger logger = LoggerFactory.getLogger(RouterServerVersionController.class);


    @GetMapping("/router/server/version")
    public String  getServerVersionInfo(Model model) {
        logger.info("Entering in method getServerVersionInfo");
        logger.info("version:0.0.1".toUpperCase());
        return "VersionInfoPlatformPage";
    }

}
