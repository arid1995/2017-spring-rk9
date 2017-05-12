package ru.bmstu.rk9.mechanics.dao;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;

import java.sql.SQLException;
import java.sql.Timestamp;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Robot;
import ru.bmstu.rk9.mechanics.models.Stacker;
import ru.bmstu.rk9.mechanics.models.SystemState;

/**
 * Created by farid on 4/21/17.
 */
public class SystemStateDao implements Dao<SystemState> {

  @Override
  public void persist(SystemState state) {
    StringBuilder query = new StringBuilder();
    Timestamp created = new Timestamp(System.currentTimeMillis());
    int newId = state.incrementAndGet();

    query.append("INSERT INTO state_log (id, created, stacker_state) VALUES(")
        .append(newId).append(",")
        .append(created.getTime()).append(",")
        .append(state.getStacker().getState()).append(")");
    try {
      Database.update(query.toString());
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
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
  }

  @Override
  public SystemState getLast() {
    try {
      return Database.select("SELECT TOP 1 * FROM state_log ORDER BY id DESC", (result) -> {
        result.next();
        int stateId = result.getInt("id");
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
    }

    return null;
  }
}
