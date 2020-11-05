package com.server.game.router.RouterServer.service;

import com.server.game.router.RouterServer.entity.UserSession;
import com.server.game.router.RouterServer.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jose eduardo on 9/30/2020.
 */
@Service
public class ClientServiceImpl implements ClientService {

    Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserSession registerClient(UserSession userSession) {
        logger.info("entering in method  registerClient");
        logger.info("Param: "+ userSession);
        if(userSession.getId() != null && userSession.getId() == 0){
            userSession.setId(null);
        }
        if(userSession.getId() == null && !userSession.getSessionId().isEmpty()){
            UserSession found = clientRepository.findBySessionId(userSession.getSessionId());
            if(found!= null){
                userSession.setId(found.getId());
            }
        }
        return clientRepository.save(userSession);
    }

    @Override
    public UserSession findBySession(String sessionId) {
        return clientRepository.findBySessionId(sessionId);
    }

    @Override
    public List<UserSession> serverClients() {
        logger.info("entering in method serverClients");
        List<UserSession> list = new ArrayList<>();
        logger.info("returning list count"+ list.size());
        clientRepository.findAll().forEach(ind ->{
            list.add(ind);
        });
        return list;
    }

    @Override
    public void deleteClient(String sessionId) {
        logger.info("entering in method  deleteClient");
        logger.info("Param: "+ sessionId);
        UserSession found = clientRepository.findBySessionId(sessionId);
        if(found != null){
            clientRepository.delete(found);
        }
    }
}