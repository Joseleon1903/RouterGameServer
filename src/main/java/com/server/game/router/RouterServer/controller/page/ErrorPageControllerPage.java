package com.server.game.router.RouterServer.controller.page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.websocket.server.PathParam;

/**
 * Created by jose de leon on 1/30/2021.
 */
@Controller
public class ErrorPageControllerPage {

    Logger logger = LoggerFactory.getLogger(DashboardControllerPage.class);

    @RequestMapping("/game/server/general/error/{id}")
    public String getGeneralErrorPage(@PathParam("id") String errorId, Model model) {

        logger.info("Entering in method getGeneralErrorPage..");


        return "error/GeneralPageError";
    }

}
