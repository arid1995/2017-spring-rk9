package ru.bmstu.rk9.mechanics.schedule;

import java.util.ArrayList;
import javafx.util.Pair;
import org.springframework.web.socket.WebSocketSession;
import ru.bmstu.rk9.mechanics.commands.Command;
import ru.bmstu.rk9.mechanics.commands.messages.Message;
import ru.bmstu.rk9.mechanics.dao.Dao;
import ru.bmstu.rk9.mechanics.dao.OrderDao;
import ru.bmstu.rk9.mechanics.dao.SystemStateDao;
import ru.bmstu.rk9.mechanics.models.Pallet;
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
  private boolean isSequenceStarted = false;
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

  //TODO: find a way to continue sequence
  private void generateNextCommand() {
    if (!isWorking) {
      return;
    }
    stacker.putPalletOnConveyor(stock.getNextPallet());
    determineCommandByProductionRules();
  }

  private void determineCommandByProductionRules() {
    Command<Message> command = null;

    ArrayList<Machine> finishedMachines = getMachinesWithState(Machine.FINISHED);
    ArrayList<Machine> freeMachines = getMachinesWithState(Machine.FREE);
    System.out.println("---------------BEGIN---------------");
    //Are there finished machines
    if (!finishedMachines.isEmpty()) {
      System.out.println("FINISHED MACHINE RULE");
      Machine finishedMachine = finishedMachines.remove(0);
      Robot robot = getRobotIfFree(finishedMachine.getMachineId());
      if (robot != null) {
        System.out.println("FREE ROBOT RULE");
        finishedMachine.openCollet();
        //TODO: what's next???
      }
    } else {
      //Are there free machines
      if (!freeMachines.isEmpty()) {
        System.out.println("FREE MACHINE RULE");
        //Are there pallets that free machine can handle
        if (conveyor.hasPallets()) {
          Pair<Machine, Pallet> suitablePallet = getSuitablePalletOnConveyor(freeMachines);
          if (suitablePallet != null) {
            System.out.println("PALETS ON CONVEYOR RULE");
            Pallet pallet = suitablePallet.getValue();
            Machine machine = suitablePallet.getKey();
            Robot robot = getRobotIfFree(machine.getMachineId());
            //Is robot free
            if (robot != null) {
              System.out.println("FREE ROBOT RULE");
              //TODO: what's next???
              conveyor.movePallet(pallet.getId(), machine.getMachineId());
            }
          }
        } else {
          System.out.println("PALLET IN STOCK RULE");
          Integer cellNumber = getSuitablePalletInStock(freeMachines);
          if (cellNumber != null) {
            stacker.takeFromTheCell(cellNumber);
          }
        }
      }
    }
    System.out.println("---------------END---------------");
  }

  private ArrayList<Machine> getMachinesWithState(Integer state) {
    ArrayList<Machine> machinesWithState = new ArrayList<>();
    for (Machine machine : machines) {
      if (machine.getState().equals(state)) {
        machinesWithState.add(machine);
      }
    }
    return machinesWithState;
  }

  private Robot getRobotIfFree(int robotId) {
    for (Robot robot : robots) {
      if (robot.getRobotId().equals(robotId)) {
        return robot;
      }
    }
    return null;
  }

  private Pair<Machine, Pallet> getSuitablePalletOnConveyor(ArrayList<Machine> machines) {
    for (Machine machine : machines) {
      Pallet pallet = conveyor.findMatchingPallet(machine);
      if (pallet != null) {
        return new Pair<Machine, Pallet>(machine, pallet);
      }
    }
    return null;
  }

  private Integer getSuitablePalletInStock(ArrayList<Machine> machines) {
    for (Machine machine : machines) {
      Integer palletNumber = null;
      palletNumber = stock.findMatchingPalletCell(machine);
      if (palletNumber != null) {
        return 0;
      }
    }
    return null;
  }


  private void reschedule() {
    scheduler.schedulePalletsInStock(orders, stock);
  }
}
