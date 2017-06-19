package ru.bmstu.rk9.mechanics.models.devices;

import ru.bmstu.rk9.mechanics.commands.RobotCommand;
import ru.bmstu.rk9.mechanics.commands.messages.RobotMessage;
import ru.bmstu.rk9.mechanics.models.Billet;
import ru.bmstu.rk9.mechanics.models.Pallet;

public class Robot extends Device {

  public static final int FREE = 0;
  public static final int BUSY = 1;
  private Integer robotId;
  private Billet takenBillet;

  public Robot(String deviceStringId, String deviceName) {
    super(deviceStringId, deviceName);
  }

  public Robot(Integer deviceId, String deviceStringId, String deviceName, Integer robotId) {
    super(deviceId, deviceStringId, deviceName);
    this.robotId = robotId;
  }

  public Robot(Integer deviceId, String deviceStringId, String deviceName, Integer state,
      Integer robotId) {
    super(deviceId, deviceStringId, deviceName, state);
    this.robotId = robotId;
  }

  public Integer getRobotId() {
    return robotId;
  }

  public void setRobotId(Integer robotId) {
    this.robotId = robotId;
  }

  public void putBilletInMachine(Machine machine) {
    RobotMessage message = new RobotMessage();
    message.putBilletInMachine();
    RobotCommand command = new RobotCommand(message);
    state = BUSY;
    sendMessageToDevice(command);
    machine.takeBillet(takenBillet);
    transaction = () -> {
      state = FREE;
    };
  }

  public void takeBilletFromMachine(Machine machine) {
    RobotMessage message = new RobotMessage();
    message.takeBilletFromMachine();
    RobotCommand command = new RobotCommand(message);
    sendMessageToDevice(command);
    state = BUSY;
    takenBillet = machine.yieldBillet();
  }

  public void takeBilletFromPallet(Pallet pallet, Integer billetPosition) {
    RobotMessage message = new RobotMessage();
    message.takeBilletFromPallet();
    RobotCommand command = new RobotCommand(message);
    sendMessageToDevice(command);
    state = BUSY;
    takenBillet = pallet.yieldBillet(billetPosition);
  }

  public void putBilletOnPallet(Pallet pallet) {
    RobotMessage message = new RobotMessage();
    message.putBilletOnPallet();
    RobotCommand command = new RobotCommand(message);
    sendMessageToDevice(command);
    state = BUSY;
    pallet.takeBillet(takenBillet);
    takenBillet = null;
    transaction = () -> {
      state = FREE;
    };
  }

  public void takeBillet(Billet billet) {
    RobotMessage message = new RobotMessage();
    message.takeBilletFromPallet();
    RobotCommand command = new RobotCommand(message);
    sendMessageToDevice(command);
    takenBillet = billet;
    state = BUSY;
  }

  public Billet yieldBillet() {
    Billet billet = takenBillet;
    takenBillet = null;
    return billet;
  }

  public int getBilletsPalletId() {
    return takenBillet.getPalletId();
  }
}
