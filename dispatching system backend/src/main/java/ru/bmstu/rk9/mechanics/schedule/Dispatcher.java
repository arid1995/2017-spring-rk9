package ru.bmstu.rk9.mechanics.schedule;

import java.util.ArrayList;
import org.springframework.web.socket.WebSocketSession;
import ru.bmstu.rk9.mechanics.commands.Command;
import ru.bmstu.rk9.mechanics.commands.messages.Message;
import ru.bmstu.rk9.mechanics.dao.Dao;
import ru.bmstu.rk9.mechanics.dao.OrderDao;
import ru.bmstu.rk9.mechanics.dao.SystemStateDao;
import ru.bmstu.rk9.mechanics.models.devices.Conveyor;
import ru.bmstu.rk9.mechanics.models.devices.Machine;
import ru.bmstu.rk9.mechanics.models.Order;
import ru.bmstu.rk9.mechanics.models.devices.Robot;
import ru.bmstu.rk9.mechanics.models.devices.Stacker;
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

  public void addOrder(Order order) {
    orders.add(order);
    reschedule();
  }

  public ArrayList<Order> getOrders() {
    return new ArrayList<>(orders);
  }

  public void save() {

  }

  public void handleMachineMessage(FeedbackMessage message) {
    Machine machine = null;

    for (Machine value : machines) {
      if (value.getDeviceStringId().equals(message.getDeviceId())) {
        System.out.println("Message received");
        machine = value;
        break;
      }
    }

    if (machine == null) {
      return;
    }
    machine.executeTransaction();
    systemStateDao.persist(systemState);
    generateNextCommand();
  }

  public void handleRobotMessage(FeedbackMessage message) {
    Robot robot = null;

    for (Robot value : robots) {
      if (value.getDeviceStringId().equals(message.getDeviceId())) {
        System.out.println("Message received");
        robot = value;
        break;
      }
    }

    if (robot == null) {
      return;
    }
    robot.executeTransaction();
    systemStateDao.persist(systemState);
    generateNextCommand();
  }

  public void handleConveyorMessage(FeedbackMessage message) {
    conveyor.executeTransaction();
    systemStateDao.persist(systemState);
    System.out.println(message.getDeviceId());
  }

  public void start() {
    isWorking = true;
    generateNextCommand();
  }

  public void stop() {
    isWorking = false;
  }

  public void handleStackerMessage(FeedbackMessage message) {
    stacker.executeTransaction();
    systemStateDao.persist(systemState);
    System.out.println(message.getDeviceId());
  }

  private void generateNextCommand() {
    if (!isWorking) {
      return;
    }
    stacker.putPalletOnConveyor(stock.getNextPallet());
    Command<Message> command = determineNextCommand();
  }

  private Command<Message> determineNextCommand() {

  }

  private void reschedule() {
    scheduler.schedulePalletsInStock(orders, stock);
  }
}
