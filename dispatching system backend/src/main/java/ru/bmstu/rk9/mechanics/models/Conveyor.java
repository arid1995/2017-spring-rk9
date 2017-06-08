package ru.bmstu.rk9.mechanics.models;

import java.util.HashMap;

public class Conveyor extends Device {
  private HashMap<Integer, Pallet> pallets = new HashMap<>();

  public Conveyor(Integer deviceId, String deviceStringId, String deviceName, Integer state) {
    super(deviceId, deviceStringId, deviceName, state);
  }

  public void movePallet(int palletId, int keyNumber) {
    Pallet pallet;
  }

  public void putPallet(Pallet pallet) {
    transaction = () -> {
      pallets.put(pallet.getId(), pallet);
    };
  }

  public void takePallet() {

  }
}
