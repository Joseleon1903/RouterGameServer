package com.server.game.router.RouterServer.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by jose de leon on 1/18/2021.
 */
@Entity
@Data
public class UserProfile {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;

    @Column(unique=true)
    private String facebookUserId;
    @Column(unique=true)
    private String guestUserId;

    private String gameName;

    private String lastTokenInfoString;
    private String profilePicture;

    @Column(nullable = false)
    private String creationDate;

    private int totalCheckerGame;
    private int totalCheckerGameWin;
    private String language;
    private boolean isGuest;

}
