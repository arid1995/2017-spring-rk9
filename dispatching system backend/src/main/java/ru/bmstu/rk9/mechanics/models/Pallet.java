package ru.bmstu.rk9.mechanics.models;

import ru.bmstu.rk9.mechanics.exceptions.MaximumCapacityException;

import java.util.HashMap;

public class Pallet {

  private int capacity;
  private int currentCount;
  private HashMap<Integer, Billet> billets;
  private Order order;
  private Integer id;
  private Integer key;

  public Pallet(int capacity, Integer id) {
    this.billets = new HashMap<>();
    this.capacity = capacity > 0 ? capacity : 1;
    this.id = id;

    currentCount = 0;
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

  public Integer getKey() {
    return key;
  }

  public void putBillet(Billet billet) {
    if (currentCount >= capacity) {
      throw new MaximumCapacityException();
    }

    billets.put(currentCount, billet);
    currentCount++;
  }

  public HashMap<Integer, Billet> getBillets() {
    return billets;
  }

  public void setKey(Integer key) {
    this.key = key;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public int removeFromOrder() {
    return order.takeBillets(currentCount);
  }
}
