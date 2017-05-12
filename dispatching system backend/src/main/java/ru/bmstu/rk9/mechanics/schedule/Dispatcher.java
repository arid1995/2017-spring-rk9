package ru.bmstu.rk9.mechanics.schedule;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.mechanics.dao.Dao;
import ru.bmstu.rk9.mechanics.dao.MachineDao;
import ru.bmstu.rk9.mechanics.dao.RobotDao;
import ru.bmstu.rk9.mechanics.dao.SystemStateDao;
import ru.bmstu.rk9.mechanics.models.Conveyor;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Order;
import ru.bmstu.rk9.mechanics.models.Robot;
import ru.bmstu.rk9.mechanics.models.Stacker;
import ru.bmstu.rk9.mechanics.models.SystemState;
import ru.bmstu.rk9.network.entities.ProductionTask;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by farid on 4/29/17.
 */
public class Dispatcher {

  private final PriorityQueue<ProductionTask> tasks = new PriorityQueue<>();
  private boolean isWorking = false;
  //private final ArrayList<Machine> machines;
  //private final ArrayList<Robot> robots;
  //private final Conveyor conveyor;
  //private final Stacker stacker;
  //private final SystemState systemState;

  private final Dao<SystemState> systemStateDao = new SystemStateDao();

  public Dispatcher(int machineNumber) {
    //systemState = systemStateDao.getLast();
    //robots = systemState.getRobotStates();
    //machines = systemState.getMachineStates();
  }

  public void addTask(ProductionTask task) {
    tasks.add(task);

    if (isWorking) {
      return;
    }

    isWorking = true;
    start();
  }

  public PriorityQueue<ProductionTask> getTasks() {
    return new PriorityQueue<>(tasks);
  }

  public void start() {

  }
}