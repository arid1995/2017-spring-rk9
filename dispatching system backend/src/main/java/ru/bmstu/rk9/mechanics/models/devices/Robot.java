package ru.bmstu.rk9.mechanics.models.devices;

import ru.bmstu.rk9.mechanics.commands.RobotCommand;
import ru.bmstu.rk9.mechanics.commands.messages.RobotMessage;
import ru.bmstu.rk9.mechanics.models.Pallet;

public class Robot extends Device {

  private Integer robotId;

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
    sendMessageToDevice(command);
    transaction = () -> {
      //TODO: think what to do after
    };
  }

  public void takeBilletFromMachine(Machine machine) {
    RobotMessage message = new RobotMessage();
    message.takeBilletFromMachine();
    RobotCommand command = new RobotCommand(message);
    sendMessageToDevice(command);
    transaction = () -> {
      //TODO: think what to do after
    };
  }

  public void takeBilletFromPallet(Pallet pallet) {
    RobotMessage message = new RobotMessage();
    message.takeBilletFromPallet();
    RobotCommand command = new RobotCommand(message);
    sendMessageToDevice(command);
    transaction = () -> {
      //TODO: think what to do after
    };
  }

  public void putBilletOnPallet(Pallet pallet) {
    RobotMessage message = new RobotMessage();
    message.putBilletOnPallet();
    RobotCommand command = new RobotCommand(message);
    sendMessageToDevice(command);
    transaction = () -> {
      //TODO: think what to do after
    };
  }
}
