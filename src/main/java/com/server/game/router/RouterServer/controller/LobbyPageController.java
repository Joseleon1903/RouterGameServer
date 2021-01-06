package com.server.game.router.RouterServer.controller;

import com.server.game.router.RouterServer.service.LobbyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by jose eduardo on 9/30/2020.
 */
@Controller
public class LobbyPageController {

    Logger logger = LoggerFactory.getLogger(LobbyPageController.class);

    @Autowired
    private LobbyService lobbyService;

    @RequestMapping("/server/lobby")
    public String getLobbyPage(Model model) {
        logger.info("Entering in method loginPage..");
        model.addAttribute("lobbyBeanList",lobbyService.avaliableLobby());
        return "LobbyPage";
    }
}
