package com.server.game.router.RouterServer.controller;

import com.server.game.router.RouterServer.entity.UserProfile;
import com.server.game.router.RouterServer.service.UserProfileService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jose de leon on 1/18/2021.
 */
@RestController
public class ProfileRestController {

    Logger logger = LoggerFactory.getLogger(ProfileRestController.class);

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping("/user/profile/create")
    ResponseEntity<UserProfile> postProfileRestResource(@RequestBody UserProfile user) {

        if(user.getFacebookUserId().isEmpty() && user.getGuestUserId().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        UserProfile userOut = userProfileService.saveOrUpdate(user);
        return ResponseEntity.ok(userOut);
    }

    @GetMapping("/user/profile/{isGuest}/{userId}")
    ResponseEntity<UserProfile> getProfileRestResource(@PathVariable("isGuest") int isGuest, @PathVariable("userId") String userId) {
        boolean bGuest = (isGuest ==1)?  true:  false;
        UserProfile userOut = userProfileService.getProfile(bGuest, userId);
        return ResponseEntity.ok(userOut);
    }

    @GetMapping("/user/profile/users")
    ResponseEntity<UserProfile[]> getProfileUsersRestResource() {
        UserProfile[] userListOut = userProfileService.getProfileUsers();
        return ResponseEntity.ok(userListOut);
    }

}
