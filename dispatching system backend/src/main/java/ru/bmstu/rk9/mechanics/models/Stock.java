package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;

public class Stock {

  private ArrayList<Pallet> pallets;
  private ArrayList<Detail> details;
  private int capacity;

  public Stock(int capacity, int palletCount) {
    this.capacity = capacity;
    pallets = new ArrayList<>();

    for (int i = 0; i < palletCount; i++) {
      Pallet pallet = new Pallet(2, i);
    }
    details = new ArrayList<>();
  }

  public int getCapacity() {
    return capacity;
  }

  public int getPalletCount() {
    return pallets.size();
  }

  public Pallet getPalletAt(int index) {
    if (index < pallets.size()) {
      return pallets.get(index);
    }
    return null;
  }

  public Pallet getNextPallet() {
    if (pallets.size() > 0) {
      return pallets.remove(0);
    }
    return null;
  }

  public void setPalletAt(int index, Pallet pallet) {
    pallets.set(index, pallet);
  }
}
