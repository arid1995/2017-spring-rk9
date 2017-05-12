package ru.bmstu.rk9.mechanics.models;

import java.util.HashMap;

/**
 * Created by farid on 5/2/17.
 */
public class Conveyor extends Device {
  private HashMap<Integer, Key> keys = new HashMap<>();

  public Conveyor(Integer deviceId, String deviceStringId, String deviceName, Integer state) {
    super(deviceId, deviceStringId, deviceName, state);
  }

  public void openKey(Integer key) {
    keys.get(key).state = 1;
  }

  public void closeKey(Integer key) {
    keys.get(key).state = 0;
  }

  private class Key {

    public byte state; //0 - closed, 1 - opened
  }
}
