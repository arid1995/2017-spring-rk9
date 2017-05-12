package ru.bmstu.rk9.mechanics.models;

import ru.bmstu.rk9.mechanics.exceptions.MaximumCapacityException;

import java.util.HashMap;

/**
 * Created by farid on 5/2/17.
 */
public class Pallet {

  private int capacity;
  private int currentCount;
  private HashMap<Integer, Billet> billets;

  public Pallet(int capacity, int technicalProcessId) {
    billets = new HashMap<>();

    this.capacity = capacity > 0 ? capacity : 0;
    for (int i = 0; i < capacity; i++) {
      billets.put(i, new Billet(technicalProcessId));
    }

    currentCount = capacity;
  }

  public Billet take() {
    if (!billets.isEmpty()) {
      currentCount--;
      return billets.get(currentCount);
    }

    return null;
  }

  public void putBillet(Billet billet) {
    if (currentCount >= capacity) {
      throw new MaximumCapacityException();
    }

    billets.put(currentCount, billet);
    currentCount++;
  }
}
