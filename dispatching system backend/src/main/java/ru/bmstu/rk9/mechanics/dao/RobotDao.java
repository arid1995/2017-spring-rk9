package ru.bmstu.rk9.mechanics.dao;

import ru.bmstu.rk9.mechanics.models.Robot;

/**
 * Created by farid on 5/11/17.
 */
public class RobotDao implements Dao<Robot> {

  @Override
  public void persist(Robot object) {

  }

  @Override
  public Robot getLast() {
    return null;
  }
}
