package ru.bmstu.rk9.mechanics.schedule;

import java.util.ArrayList;
import java.util.HashMap;
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
  private static final int STACKER_SEQUENCE = 0;
  private static final int MACHINE_LOAD_SEQUENCE = 1;
  private static final int MACHINE_UNLOAD_SEQUENCE = 2;

  private boolean isWorking = false;
  private final ArrayList<Machine> machines;
  private final ArrayList<Robot> robots;
  private final HashMap<Integer, Pallet> pallets = new HashMap<>();
  private final Conveyor conveyor;
  private final Stacker stacker;
  private final Stock stock;
  private final SystemState systemState;
  private ArrayList<Order> orders = new ArrayList<>();
  //FUCK THIS!!!
  private boolean isSequenceStarted = false;
  private int sequenceCounter = 0;
  private int sequenceDescriptor = 0;
  private Machine activeMachine = null;
  private Robot activeRobot = null;
  //FUCK UNTIL HERE!!!
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
        System.out.println("Устройство присоединено к модели, ID: " + deviceId);
        return;
      }
    }

    for (Machine machine : machines) {
      if (machine.getDeviceStringId().equals(deviceId)) {
        machine.setSession(session);
        System.out.println("Устройство присоединено к модели, ID: " + deviceId);
        return;
      }
    }

    if (stacker.getDeviceStringId().equals(deviceId)) {
      stacker.setSession(session);
      System.out.println("Устройство присоединено к модели, ID: " + deviceId);
      return;
    }

    if (conveyor.getDeviceStringId().equals(deviceId)) {
      conveyor.setSession(session);
      System.out.println("Устройство присоединено к модели, ID: " + deviceId);
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

  public void handleMachineMessage(FeedbackMessage message,  String payload) {
    Machine machine = null;
    System.out.println("СООБЩЕНИЕ ОБРАТНОЙ СВЯЗИ СТАНКА: " + payload);

    for (Machine value : machines) {
      if (value.getDeviceStringId().equals(message.getDeviceId())) {
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

  public void handleRobotMessage(FeedbackMessage message,  String payload) {
    Robot robot = null;
    System.out.println("СООБЩЕНИЕ ОБРАТНОЙ СВЯЗИ РОБОТА: " + payload);

    for (Robot value : robots) {
      if (value.getDeviceStringId().equals(message.getDeviceId())) {
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

  public void handleConveyorMessage(FeedbackMessage message, String payload) {
    conveyor.executeTransaction();
    systemStateDao.persist(systemState);
    System.out.println("СООБЩЕНИЕ ОБРАТНОЙ СВЯЗИ КОНВЕЙЕРА: " + payload);
    generateNextCommand();
  }

  public void start() {
    isWorking = true;
    generateNextCommand();
  }

  public void stop() {
    isWorking = false;
  }

  public void handleStackerMessage(FeedbackMessage message, String payload) {
    stacker.executeTransaction();
    systemStateDao.persist(systemState);
    System.out.println("СООБЩЕНИЕ ОБРАТНОЙ СВЯЗИ ШТАБЕЛЕРА: " + payload);
    generateNextCommand();
  }

  //TODO: find a way to continue sequence
  private void generateNextCommand() {
    if (!isWorking) {
      return;
    }
    if (!isSequenceStarted) {
      determineCommandByProductionRules();
      return;
    }
    switch (sequenceDescriptor) {
      case MACHINE_LOAD_SEQUENCE:
        continueLoadSequence();
        break;
      case MACHINE_UNLOAD_SEQUENCE:
        continueUnloadSequence();
        break;
    }
  }

  private void continueUnloadSequence() {
    switch (sequenceCounter) {
      case 1:
        activeRobot.takeBilletFromMachine(activeMachine);
        break;
      case 2:
        conveyor.movePallet(activeRobot.getBilletsPalletId(), activeRobot.getRobotId());
        break;
      case 3: {
        conveyor.takeBilletOnKey(activeRobot.yieldBillet(), activeRobot.getRobotId());
        isSequenceStarted = false;
        sequenceCounter = 0;
        activeRobot = null;
        activeMachine = null;
        generateNextCommand();
        return;
      }
    }
    sequenceCounter++;
  }

  private void continueLoadSequence() {
    switch (sequenceCounter) {
      case 1:
        activeRobot.takeBillet(conveyor.yieldBilletOnKey(activeRobot.getRobotId()));
        break;
      case 2:
        activeRobot.putBilletInMachine(activeMachine);
        break;
      case 3:
        activeMachine.closeCollet();
        break;
      case 4: {
        activeMachine.startProcess();
        isSequenceStarted = false;
        sequenceCounter = 0;
        activeRobot = null;
        activeMachine = null;
        generateNextCommand();
        return;
      }
    }
    sequenceCounter++;
  }

  private void determineCommandByProductionRules() {
    Command<Message> command = null;

    ArrayList<Machine> finishedMachines = getMachinesWithState(Machine.FINISHED);
    ArrayList<Machine> freeMachines = getMachinesWithState(Machine.FREE);
    System.out.println("---------------НАЧАЛО ПРОСМОТРА ПРАВИЛ---------------");
    //Are there finished machines
    if (!finishedMachines.isEmpty()) {
      System.out.println("ЕСТЬ НЕДАВНО ЗАВЕРШИВШИЕ СТАНКИ");
      Machine finishedMachine = finishedMachines.remove(0);
      Robot robot = getRobotIfFree(finishedMachine.getMachineId());
      if (robot != null) {
        System.out.println("ЕСТЬ СВОБОДНЫЕ РОБОТЫ");
        finishedMachine.openCollet();
        isSequenceStarted = true;
        sequenceDescriptor = MACHINE_UNLOAD_SEQUENCE;
        sequenceCounter = 1;
        activeMachine = finishedMachine;
        activeRobot = robot;
      }
    } else {
      //Are there free machines
      if (!freeMachines.isEmpty()) {
        System.out.println("ЕСТЬ СВОБОДНЫЕ СТАНКИ");
        //Are there pallets that free machine can handle
        if (conveyor.hasPallets()) {
          Pair<Machine, Pallet> suitablePallet = getSuitablePalletOnConveyor(freeMachines);
          if (suitablePallet != null) {
            System.out.println("ЕСТЬ ПАЛЛЕТЫ НА КОНВЕЙЕРЕ");
            Pallet pallet = suitablePallet.getValue();
            Machine machine = suitablePallet.getKey();
            Robot robot = getRobotIfFree(machine.getMachineId());
            //Is robot free
            if (robot != null) {
              System.out.println("ЕСТЬ СВОБОДНЫЕ РОБОТЫ");
              conveyor.movePallet(pallet.getId(), robot.getRobotId());
              isSequenceStarted = true;
              sequenceDescriptor = MACHINE_LOAD_SEQUENCE;
              sequenceCounter = 1;
              activeMachine = machine;
              activeRobot = robot;
            }
          }
        } else {
          System.out.println("ЕСТЬ ПАЛЛЕТЫ НА СКЛАДЕ");
          Integer cellNumber = getSuitablePalletInStock(freeMachines);
          if (cellNumber != null) {
            stacker.takeFromTheCell(cellNumber, stock);
            conveyor.takePallet(stacker.yieldPallet());
          }
        }
      }
    }
    System.out.println("---------------КОНЕЦ ПРОСМОТРА ПРАВИЛ---------------");
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
        return palletNumber;
      }
    }
    return null;
  }


  private void reschedule() {
    scheduler.schedulePalletsInStock(orders, stock);
  }
}
