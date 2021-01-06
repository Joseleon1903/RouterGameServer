package com.server.game.router.RouterServer.controller;

import com.server.game.router.RouterServer.config.AppUtilContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebSocketController {

    Logger logger = LoggerFactory.getLogger(WebSocketController.class);


    @RequestMapping({"/websocket", "/"})
    public String getWebSocket() {

        String context = AppUtilContext.getBaseUrl();
        logger.info("context: "+ context);

        return "ws-broadcast";
    }

}