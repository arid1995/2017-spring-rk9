package ru.bmstu.rk9.network.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import ru.bmstu.rk9.mechanics.models.Order;
import ru.bmstu.rk9.mechanics.schedule.Dispatcher;
import ru.bmstu.rk9.network.entities.FeedbackMessage;
import ru.bmstu.rk9.network.entities.FeedbackMessageEntity;
import ru.bmstu.rk9.network.entities.ProductionTaskEntity;

import java.util.PriorityQueue;

/**
 * Created by farid on 5/2/17.
 */
@Service
public class TaskService {

  private Dispatcher dispatcher = new Dispatcher();

  public void addOrder(Order order) {
    dispatcher.addTask(order);
  }

  public ArrayList<Order> getTaskQueue() {
    return dispatcher.getOrders();
  }

  public void handleMessage(FeedbackMessageEntity messageEntity) {
    for (FeedbackMessage message : messageEntity.getMessages()) {
      dispatcher.handleMessage(message, messageEntity.getMessageType());
    }
  }

  public void reload() {
    dispatcher.save();
    dispatcher = new Dispatcher();
  }
}
