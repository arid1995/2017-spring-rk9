package ru.bmstu.rk9.mechanics.schedule;

import ru.bmstu.rk9.mechanics.dao.Dao;
import ru.bmstu.rk9.mechanics.dao.SystemStateDao;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Robot;
import ru.bmstu.rk9.mechanics.models.SystemState;
import ru.bmstu.rk9.network.entities.ProductionTaskEntity;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by farid on 4/29/17.
 */
public class Dispatcher {

  private final PriorityQueue<ProductionTaskEntity> tasks = new PriorityQueue<>();
  private boolean isWorking = false;
  private final ArrayList<Machine> machines;
  private final ArrayList<Robot> robots;
  //private final Conveyor conveyor;
  //private final Stacker stacker;
  private final SystemState systemState;

  private final Dao<SystemState> systemStateDao = new SystemStateDao();

  public Dispatcher(int machineNumber) {
    systemState = systemStateDao.getLast();
    robots = systemState.getRobotStates();
    machines = systemState.getMachineStates();
  }

  public void addTask(ProductionTaskEntity task) {
    tasks.add(task);

    if (isWorking) {
      return;
    }

    isWorking = true;
    start();
  }

  public PriorityQueue<ProductionTaskEntity> getTasks() {
    return new PriorityQueue<>(tasks);
  }

  public void start() {

  }
}