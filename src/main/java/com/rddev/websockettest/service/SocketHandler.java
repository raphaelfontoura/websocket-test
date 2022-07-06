package com.rddev.websockettest.service;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SocketHandler extends TextWebSocketHandler {

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String payload = (String) message.getPayload();
        session.sendMessage(new TextMessage(payload));
    }
}
