package ru.bmstu.rk9.mechanics.dao;

import ru.bmstu.rk9.mechanics.models.Order;

/**
 * Created by farid on 5/11/17.
 */
public class OrderDao implements Dao<Order> {

  @Override
  public void persist(Order object) {

  }

  @Override
  public Order getLast() {
    return null;
  }
}
