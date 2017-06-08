package ru.bmstu.rk9.network.websocket;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.bmstu.rk9.network.entities.FeedbackMessageEntity;
import ru.bmstu.rk9.network.services.TaskService;

public class MachineMessageHandler extends MessageHandler {
  public MachineMessageHandler(TaskService taskService) {
    super(taskService);
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage textMessage)
      throws Exception {
    FeedbackMessageEntity message = mapper.readValue(textMessage.toString(), FeedbackMessageEntity.class);
    taskService.handleConveyorMessage(message);
  }
}
