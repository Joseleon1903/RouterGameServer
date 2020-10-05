package com.server.game.router.RouterServer.service;

import org.springframework.web.socket.WebSocketSession;

import javax.websocket.Session;

/**
 * Created by jose eduardo on 10/1/2020.
 */
public interface ConnectionService {

   void NotifySessionConnection(WebSocketSession session);

   String NotifySessionDisconnection(WebSocketSession session);

}
