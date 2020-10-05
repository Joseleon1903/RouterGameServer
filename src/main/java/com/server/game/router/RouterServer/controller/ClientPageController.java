package com.server.game.router.RouterServer.controller;

import com.server.game.router.RouterServer.service.ClientService;
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
public class ClientPageController {

    Logger logger = LoggerFactory.getLogger(ClientPageController.class);

    @Autowired
    private ClientService clientService;

    @RequestMapping("/server/clients")
    public String getLobbyPage(Model model) {
        logger.info("Entering in method getLobbyPage..");
        model.addAttribute("clientBeanList",clientService.serverClients());
        return "ClientPage";
    }
}



