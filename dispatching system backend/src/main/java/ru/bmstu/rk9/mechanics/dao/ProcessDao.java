package ru.bmstu.rk9.mechanics.dao;

import ru.bmstu.rk9.mechanics.models.Process;

/**
 * Created by farid on 5/11/17.
 */
public class ProcessDao implements Dao<Process> {

  @Override
  public void persist(Process object) {

  }

  @Override
  public Process getLast() {
    return null;
  }
}
