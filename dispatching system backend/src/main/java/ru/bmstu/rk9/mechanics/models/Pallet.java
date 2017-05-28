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
  private Order order;
  private Integer id;

  public Pallet(int capacity, Integer id) {
    this.billets = new HashMap<>();
    this.capacity = capacity > 0 ? capacity : 1;
    this.id = id;

    currentCount = capacity;
  }

  public Billet take() {
    if (!billets.isEmpty()) {
      currentCount--;
      return billets.get(currentCount);
    }

    return null;
  }

  public Integer getId() {
    return id;
  }

  public void putBillet(Billet billet) {
    if (currentCount >= capacity) {
      throw new MaximumCapacityException();
    }

    billets.put(currentCount, billet);
    currentCount++;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public int removeFromOrder() {
    return order.takeBillets(currentCount);
  }
}
