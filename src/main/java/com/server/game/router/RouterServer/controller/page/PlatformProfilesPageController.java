package com.server.game.router.RouterServer.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jose de leon on 1/20/2021.
 */
@Controller
public class PlatformProfilesPageController {

    Logger logger = LoggerFactory.getLogger(LobbyControllerPage.class);

    @RequestMapping("/game/server/profiles")
    public String getPlatformProfilesPage(Model model) {
        logger.info("Entering in method getPlatformProfilesPage..");
        return "CheckerGameProfiesPage";
    }
}
