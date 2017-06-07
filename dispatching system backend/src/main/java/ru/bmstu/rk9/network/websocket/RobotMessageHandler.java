package ru.bmstu.rk9.network.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.bmstu.rk9.network.services.TaskService;

/**
 * Created by farid on 6/8/17.
 */
@Service
public class RobotMessageHandler extends TextWebSocketHandler {
  final TaskService taskService;

  public RobotMessageHandler(TaskService taskService) {
    this.taskService = taskService;
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    session.sendMessage(new TextMessage("You are now connected to the server. This is the first message."));
    System.out.print("Connected robot" + session.getId());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
    System.out.println("Message received: " + textMessage.getPayload());
  }
}
