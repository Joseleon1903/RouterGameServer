package com.server.game.router.RouterServer.service;

import com.server.game.router.RouterServer.entity.UserProfile;
import com.server.game.router.RouterServer.repository.ProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose de leon on 1/18/2021.
 */
@Service
public class UserProfileServiceImpl implements UserProfileService {

    Logger logger = LoggerFactory.getLogger(UserProfileServiceImpl.class);

    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserProfile saveOrUpdate(UserProfile profile) {
        logger.info("Entering in method saveOrUpdate");
        logger.info("param : "+profile);
        logger.info("Save tor Update user information");
        UserProfile userProfile = profileRepository.save(profile);
        logger.info("finish Save tor Update user information");
        return userProfile;
    }

    @Override
    public UserProfile getProfile(boolean isGuest, String id) {
        logger.info("Entering in method getProfile");
        logger.info("param isGuest : "+isGuest);
        logger.info("param id : "+id);
        logger.info("get user profile ");
        UserProfile userProfile;
        if(isGuest){
            logger.info("user profile is Guest");
            userProfile =profileRepository.findByGuestUserId(id);
        }else{
            logger.info("user profile is not Guest");
            userProfile =profileRepository.findByFacebookUserId(id);
        }
        return userProfile;
    }

    @Override
    public UserProfile[] getProfileUsers() {
        logger.info("Entering in method getProfileUsers");
        logger.info("get users profile ");
        List<UserProfile> list = new ArrayList<>();
        Iterable<UserProfile> userProfiles =profileRepository.findAll();
        userProfiles.forEach(item ->{
            list.add(item);
        });
        return list.toArray(new UserProfile[0]);
    }

    @Override
    public void deleteProfileUser(long id) {
        logger.info("Entering in method deleteProfileUser");
        logger.info("param id: "+id);
        logger.info("delete user information");
        profileRepository.deleteById(id);
        logger.info("finish Save tor Update user information");
    }

    @Override
    public UserProfile deleteProfileUser(UserProfile user) {
        logger.info("Entering in method deleteProfileUser");
        logger.info("param id: "+user);
        logger.info("delete user information");
        profileRepository.delete(user);
        logger.info("finish Save tor Update user information");
        return user;
    }


}
