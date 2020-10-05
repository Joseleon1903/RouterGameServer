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
public class Lobby {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private String lobbyCode;

    private int capacity;

    private int playerCount;

    private String sessionIdentifier;

    private String status;

}
