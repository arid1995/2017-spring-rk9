package ru.bmstu.rk9.mechanics.schedule;

import java.util.ArrayList;
import org.springframework.web.socket.WebSocketSession;
import ru.bmstu.rk9.mechanics.dao.Dao;
import ru.bmstu.rk9.mechanics.dao.OrderDao;
import ru.bmstu.rk9.mechanics.dao.SystemStateDao;
import ru.bmstu.rk9.mechanics.models.Conveyor;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Order;
import ru.bmstu.rk9.mechanics.models.Robot;
import ru.bmstu.rk9.mechanics.models.Stacker;
import ru.bmstu.rk9.mechanics.models.Stock;
import ru.bmstu.rk9.mechanics.models.SystemState;
import ru.bmstu.rk9.network.entities.FeedbackMessage;

public class Dispatcher {

  private boolean isWorking = false;
  private final ArrayList<Machine> machines;
  private final ArrayList<Robot> robots;
  private final Conveyor conveyor;
  private final Stacker stacker;
  private final Stock stock;
  private final SystemState systemState;
  private ArrayList<Order> orders = new ArrayList<>();
  Scheduler scheduler = new Scheduler();

  private final Dao<SystemState> systemStateDao = new SystemStateDao();

  public Dispatcher() {
    systemState = systemStateDao.getLast();
    robots = systemState.getRobotStates();
    machines = systemState.getMachineStates();
    stacker = new Stacker(0, 0);
    conveyor = new Conveyor(0, "ec2750ed-45d9-4335-8dbd-08f86408a2b1",
        "conveyor", 0);
    int STOCK_CAPACITY = 20;
    stock = new Stock(STOCK_CAPACITY, STOCK_CAPACITY);
    orders = new OrderDao().getUnfinishedOrders();
    reschedule();
    start();
  }

  public void setWebSocketSession(String deviceId, WebSocketSession session) {
    //Looking for matching device id
    for (Robot robot : robots) {
      if (robot.getDeviceStringId().equals(deviceId)) {
        robot.setSession(session);
        System.out.println("Attached session for: " + deviceId);
        return;
      }
    }

    for (Machine machine : machines) {
      if (machine.getDeviceStringId().equals(deviceId)) {
        machine.setSession(session);
        System.out.println("Attached session for: " + deviceId);
        return;
      }
    }

    if (stacker.getDeviceStringId().equals(deviceId)) {
      stacker.setSession(session);
      System.out.println("Attached session for: " + deviceId);
      return;
    }

    if (conveyor.getDeviceStringId().equals(deviceId)) {
      conveyor.setSession(session);
      System.out.println("Attached session for: " + deviceId);
    }
  }

  public void addTask(Order order) {
    orders.add(order);
    reschedule();
  }

  public ArrayList<Order> getOrders() {
    return new ArrayList<>(orders);
  }

  public void save() {

  }

  public void handleMachineMessage(FeedbackMessage message) {

  }

  public void handleRobotMessage(FeedbackMessage message) {

  }

  public void handleConveyorMessage(FeedbackMessage message) {

  }

  public void handleStackerMessage(FeedbackMessage message) {

  }

  private void start() {
    stacker.putPalletOnConveyor(stock.getNextPallet());
  }

  private void reschedule() {
    scheduler.schedulePalletsInStock(orders, stock);
  }
}
