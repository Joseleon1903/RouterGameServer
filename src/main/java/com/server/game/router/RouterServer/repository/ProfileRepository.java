package com.server.game.router.RouterServer.repository;

import com.server.game.router.RouterServer.entity.UserProfile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jose de leon on 1/18/2021.
 */
public interface ProfileRepository extends CrudRepository<UserProfile, Long> {

    UserProfile findByFacebookUserId(String facebookUserId);

    UserProfile findByGuestUserId(String guestUserId);
}
