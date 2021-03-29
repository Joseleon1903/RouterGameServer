package com.server.game.router.RouterServer.controller.page;

import com.server.game.router.RouterServer.service.UserProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by jose de leon on 1/20/2021.
 */
@Controller
public class PlatformProfilesPageController {

    Logger logger = LoggerFactory.getLogger(LobbyControllerPage.class);

    @Autowired
    private UserProfileService userProfileService;

    @RequestMapping("/game/server/profiles")
    public String getPlatformProfilesPage(Model model) {
        logger.info("Entering in method getPlatformProfilesPage..");
        model.addAttribute("ptofilesList",userProfileService.getProfileUsers());
        return "CheckerGameProfiesPage";
    }


    @RequestMapping(value = "/game/server/profile/delete", method = RequestMethod.POST)
    public String postDeleteProfile(@RequestParam("id") String deleteId){
        logger.info("entry point display postDeleteProfile");
        logger.info("param: "+ deleteId);
        long id = Long.parseLong(deleteId);
        userProfileService.deleteProfileUser(id);
        logger.info("Validation input success");
        return "redirect:/game/server/profiles";
    }



}
