package ru.bmstu.rk9.mechanics.models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by farid on 5/2/17.
 */
public class Robot extends Device {
  private static AtomicInteger idGenerator = new AtomicInteger();
  private Integer robotId;

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

  public void putBilletInMachine(Billet billet) {

  }

  public void takeBilletFromMachine(Billet billet) {

  }
}
