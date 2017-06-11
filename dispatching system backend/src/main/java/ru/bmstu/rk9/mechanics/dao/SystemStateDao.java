package ru.bmstu.rk9.mechanics.dao;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;

import java.sql.SQLException;
import java.sql.Timestamp;
import ru.bmstu.rk9.mechanics.models.devices.Machine;
import ru.bmstu.rk9.mechanics.models.devices.Robot;
import ru.bmstu.rk9.mechanics.models.devices.Stacker;
import ru.bmstu.rk9.mechanics.models.SystemState;

public class SystemStateDao implements Dao<SystemState> {

  private static AtomicInteger idGenerator = new AtomicInteger(-1);

  @Override
  public int persist(SystemState state) {
    StringBuilder query = new StringBuilder();
    Timestamp created = new Timestamp(System.currentTimeMillis());
    int newId;

    try {
      newId = Utils.getNextId("state_log", "state_id", idGenerator);
      query.append("INSERT INTO state_log (state_id, created, stacker_state) VALUES(")
          .append(newId).append(",")
          .append(created.getTime()).append(",")
          .append(state.getStacker().getState()).append(")");
      Database.update(query.toString());
      state.setSystemStateId(newId);
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      return -1;
    }

    RobotStateDao robotStateDao = new RobotStateDao();
    MachineStateDao machineStateDao = new MachineStateDao();

    for (Robot robot : state.getRobotStates()) {
      robotStateDao.persist(robot);
      robot.setStateId(newId);
    }
    for (Machine machine : state.getMachineStates()) {
      machineStateDao.persist(machine);
      machine.setStateId(newId);
    }
    return newId;
  }

  @Override
  public SystemState getLast() {
    try {
      return Database.select("SELECT TOP 1 * FROM state_log ORDER BY state_id DESC", (result) -> {
        if (!result.next()) {
          Timestamp created = new Timestamp(System.currentTimeMillis());
          SystemState systemState = new SystemState(0, created, new Stacker(0, 0));
          ArrayList<Robot> robots = new RobotDao().getAll();
          ArrayList<Machine> machines = new MachineDao().getAll();
          for (Robot robot : robots) {
            robot.setState(0);
          }
          for (Machine machine: machines) {
            machine.setState(0);
          }
          systemState.setRobotStates(robots);
          systemState.setMachineStates(machines);
          return systemState;
        }
        int stateId = result.getInt("state_id");
        Timestamp created = result.getTimestamp("created");
        int stackerState = result.getInt("stacker_state");
        //TODO: decide what to do with the stacker
        SystemState systemState = new SystemState(stateId, created, new Stacker(0, 0));

        RobotStateDao robotStateDao = new RobotStateDao();
        MachineStateDao machineStateDao = new MachineStateDao();
        systemState.setRobotStates(robotStateDao.getByStateId(stateId));
        systemState.setMachineStates(machineStateDao.getByStateId(stateId));

        return systemState;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      return null;
    }
  }

  @Override
  public ArrayList<SystemState> getAll() {
    return null;
  }
}
