package com.server.game.router.RouterServer.entity;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jose eduardo on 9/30/2020.
 */
@Entity
@Data
public class UserSession {

    @Id
    @GeneratedValue
    private Long id;

    private String playerName;

    private Boolean isHost;

    private String sessionId;

    private String lobbyClient;

}
