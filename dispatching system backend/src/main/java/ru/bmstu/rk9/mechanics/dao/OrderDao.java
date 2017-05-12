package ru.bmstu.rk9.mechanics.dao;

import java.util.ArrayList;
import ru.bmstu.rk9.mechanics.models.Order;

/**
 * Created by farid on 5/11/17.
 */
public class OrderDao implements Dao<Order> {

  @Override
  public int persist(Order object) {
    return 0;
  }

  @Override
  public Order getLast() {
    return null;
  }

  @Override
  public ArrayList<Order> getAll() {
    return null;
  }
}
