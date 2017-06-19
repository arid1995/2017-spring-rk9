package ru.bmstu.rk9.network.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;
import ru.bmstu.rk9.mechanics.dao.OrderDao;
import ru.bmstu.rk9.mechanics.models.Order;
import ru.bmstu.rk9.mechanics.schedule.Dispatcher;
import ru.bmstu.rk9.network.entities.FeedbackMessage;
import ru.bmstu.rk9.network.entities.FeedbackMessageEntity;

@Service
public class TaskService {

  private Dispatcher dispatcher = new Dispatcher();

  public void addOrder(Order order) {
    //dispatcher.addOrder(order);
    OrderDao orderDao = new OrderDao();
    orderDao.persist(order);
  }

  public ArrayList<Order> getTaskQueue() {
    return dispatcher.getOrders();
  }

  public void handleMachineMessage(FeedbackMessageEntity messageEntity, String payload) {
    for (FeedbackMessage message : messageEntity.getMessages()) {
      dispatcher.handleMachineMessage(message, payload);
    }
  }

  public void handleRobotMessage(FeedbackMessageEntity messageEntity, String payload) {
    for (FeedbackMessage message : messageEntity.getMessages()) {
      dispatcher.handleRobotMessage(message, payload);
    }
  }

  public void handleStackerMessage(FeedbackMessageEntity messageEntity, String payload) {
    for (FeedbackMessage message : messageEntity.getMessages()) {
      dispatcher.handleStackerMessage(message, payload);
    }
  }

  public void handleConveyorMessage(FeedbackMessageEntity messageEntity, String payload) {
    for (FeedbackMessage message : messageEntity.getMessages()) {
      dispatcher.handleConveyorMessage(message, payload);
    }
  }

  public void setWebSocketSession(String deviceId, WebSocketSession session) {
    dispatcher.setWebSocketSession(deviceId, session);
  }

  public void startDispatching() {
    dispatcher.start();
  }

  public void stopDispatching() {
    dispatcher.stop();
  }

  public void reload() {
    dispatcher.save();
    dispatcher = new Dispatcher();
  }
}
