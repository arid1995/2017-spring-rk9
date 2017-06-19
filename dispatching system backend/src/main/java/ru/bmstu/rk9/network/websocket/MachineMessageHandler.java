package ru.bmstu.rk9.network.websocket;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.bmstu.rk9.network.entities.FeedbackMessageEntity;
import ru.bmstu.rk9.network.services.TaskService;

public class MachineMessageHandler extends MessageHandler {
  public MachineMessageHandler(TaskService taskService) {
    super(taskService);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage textMessage) {
    FeedbackMessageEntity message = null;
    try {
      message = mapper.readValue(textMessage.getPayload(), FeedbackMessageEntity.class);
    } catch (IOException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
    }
    taskService.handleMachineMessage(message, textMessage.getPayload());
  }
}
