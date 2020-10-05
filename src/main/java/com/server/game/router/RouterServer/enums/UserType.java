package com.server.game.router.RouterServer.enums;

/**
 * Created by jose eduardo on 10/1/2020.
 */
public enum UserType {

    ANONIMOUS("ANONIMOUS"),
    LOBBYHOST("LOBBY HOST");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }



}
