package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;
import java.util.HashMap;
import ru.bmstu.rk9.mechanics.models.devices.Machine;

public class Stock {

  private HashMap<Integer, Pallet> pallets;
  private int capacity;

  public Stock(int capacity, int palletCount) {
    this.capacity = capacity;
    pallets = new HashMap<>();

    for (int i = 0; i < palletCount; i++) {
      Pallet pallet = new Pallet(2, i);
      pallets.put(i, pallet);
    }
  }

  public int getCapacity() {
    return capacity;
  }

  public int getPalletCount() {
    return pallets.size();
  }

  public Pallet yieldPalletAt(int index) {
    if (index < pallets.size()) {
      return pallets.remove(index);
    }
    return null;
  }

  public Pallet getNextPallet() {
    if (pallets.size() > 0) {
      return pallets.remove(0);
    }
    return null;
  }

  public Integer findMatchingPalletCell(Machine machine) {
    for (HashMap.Entry<Integer, Pallet> palletEntry : pallets.entrySet()) {
      Pallet pallet = palletEntry.getValue();
      for (HashMap.Entry<Integer, Billet> billetEntry : pallet.getBillets().entrySet()) {
        Process nextProcess = billetEntry.getValue().getNextProcess();
        if (machine.hasProgram(nextProcess.getProgramName())) {
          return palletEntry.getKey();
        }
      }
    }
    return null;
  }

  public void setPalletAt(int index, Pallet pallet) {
    pallets.put(index, pallet);
  }
}
