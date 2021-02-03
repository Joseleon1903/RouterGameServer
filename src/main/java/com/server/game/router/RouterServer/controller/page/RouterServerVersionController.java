package com.server.game.router.RouterServer.controller.page;

import com.server.game.router.RouterServer.bean.ServerInformationBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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


    @Autowired
    private ServerInformationBean informationBean;


    @GetMapping("/router/server/version")
    public String  getServerVersionInfo(Model model) {
        logger.info("Entering in method getServerVersionInfo");

        informationBean.setServerName("Router Server Game");
        informationBean.setServerVersion("0.0.1-SNAPSHOT-STABLE");
        informationBean.setBaseDomain("http://localhost:8085/");

        informationBean.setAppName("Router");
        informationBean.setAppVersion("Router-Game-Server-0.0.1-SNAPSHOT");
        informationBean.setAppRestBaseDomain("http://localhost:8085/");

        model.addAttribute("informationBean", informationBean);

        logger.info("version:0.0.1".toUpperCase());
        return "VersionInfoPlatformPage";
    }

}
