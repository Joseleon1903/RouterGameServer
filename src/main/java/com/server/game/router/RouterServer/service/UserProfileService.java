package com.server.game.router.RouterServer.service;

import com.server.game.router.RouterServer.entity.UserProfile;

/**
 * Created by jose de leon on 1/18/2021.
 */
public interface UserProfileService {

    UserProfile saveOrUpdate(UserProfile profile);

    UserProfile getProfile(boolean isGuest, String id);

    UserProfile getProfile(String playerId);

    UserProfile[] getProfileUsers();

    void deleteProfileUser(long id);

    UserProfile deleteProfileUser(UserProfile user);


}
