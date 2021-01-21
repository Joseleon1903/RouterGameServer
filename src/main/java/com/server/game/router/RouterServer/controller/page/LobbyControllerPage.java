package com.server.game.router.RouterServer.controller.page;

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
public class LobbyControllerPage {

    Logger logger = LoggerFactory.getLogger(LobbyControllerPage.class);

    @Autowired
    private LobbyService lobbyService;

    @RequestMapping("/game/server/lobby")
    public String getCheckerLobbyPage(Model model) {
        logger.info("Entering in method getCheckerLobbyPage..");
        model.addAttribute("lobbyBeanList",lobbyService.avaliableLobby());
        return "CheckerGameLobbiesPage";
    }
}
