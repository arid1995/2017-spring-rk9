package ru.bmstu.rk9.network.websocket;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.bmstu.rk9.network.entities.FeedbackMessageEntity;
import ru.bmstu.rk9.network.services.TaskService;

/**
 * Created by farid on 6/8/17.
 */
@Service
public class RobotMessageHandler extends MessageHandler {
  public RobotMessageHandler(TaskService taskService) {
    super(taskService);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws Exception {
    FeedbackMessageEntity message = mapper.readValue(textMessage.toString(), FeedbackMessageEntity.class);
    taskService.handleConveyorMessage(message);
  }
}
