package ru.bmstu.rk9.mechanics.models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by farid on 5/2/17.
 */
public class Robot extends Device {
  private static AtomicInteger idGenerator = new AtomicInteger();
  private Integer robotId;

  public Robot(Integer robotId, Integer deviceId, Integer state) {
    super(deviceId, state);
  }

  public Robot(Integer robotId, Integer deviceId, String deviceStringId,
      String deviceName, Integer state) {
    super(deviceId, deviceStringId, deviceName, state);
    this.robotId = robotId;
  }

  public void putBilletInMachine(Billet billet) {

  }

  public void takeBilletFromMachine(Billet billet) {

  }

  @Override
  public int getDeviceId() {
    return 0;
  }

  @Override
  public int incrementAndGet() {
    return 0;
  }
}
