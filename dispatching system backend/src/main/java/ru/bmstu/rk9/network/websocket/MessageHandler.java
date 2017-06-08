package ru.bmstu.rk9.network.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.bmstu.rk9.network.services.TaskService;

public class MessageHandler extends TextWebSocketHandler {
  protected final TaskService taskService;
  protected final ObjectMapper mapper = new ObjectMapper();

  public MessageHandler(TaskService taskService) {
    this.taskService = taskService;
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    taskService.setWebSocketSession(
        session.getUri().toString().replace("/", ""), session);
  }
}
