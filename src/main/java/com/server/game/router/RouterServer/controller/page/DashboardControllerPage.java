package com.server.game.router.RouterServer.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jose de leon on 1/21/2021.
 */
@Controller
public class DashboardControllerPage {

    Logger logger = LoggerFactory.getLogger(DashboardControllerPage.class);

    @RequestMapping("/game/server/dashboard")
    public String getDashboardPage(Model model) {
        logger.info("Entering in method getDashboardPage..");
        return "DashboardPage";
    }

}
