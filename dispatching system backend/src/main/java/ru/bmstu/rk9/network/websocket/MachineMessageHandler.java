package ru.bmstu.rk9.network.websocket;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * Created by farid on 6/7/17.
 */
public class MachineMessageHandler extends TextWebSocketHandler {
  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    session.sendMessage(new TextMessage("{\"messageType\":\"dd53c9c33eaedfbdd766\"}"));
    System.out.print("Connected machine" + session.getId());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
    System.out.println("Message received: " + textMessage.getPayload());
    session.sendMessage(new TextMessage("{\"messageType\":\"dd53c9c33eaedfbdd766\"}"));
  }
}
