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
public class IndexControllerPage {

    Logger logger = LoggerFactory.getLogger(IndexControllerPage.class);

    @RequestMapping({"/server/index","/"})
    public String getPlatformProfilesPage(Model model) {
        logger.info("Entering in method IndexControllerPage..");
        return "IndexPage";
    }


}
