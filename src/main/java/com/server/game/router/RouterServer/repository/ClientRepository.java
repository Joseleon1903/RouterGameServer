package com.server.game.router.RouterServer.repository;

import com.server.game.router.RouterServer.entity.UserSession;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jose eduardo on 9/30/2020.
 */
public interface ClientRepository  extends CrudRepository<UserSession, Long>{

    UserSession findBySessionId(String sessionId);
}
