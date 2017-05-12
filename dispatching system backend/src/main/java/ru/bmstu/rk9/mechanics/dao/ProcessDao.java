package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.Process;

/**
 * Created by farid on 5/11/17.
 */
public class ProcessDao implements Dao<Process> {

  @Override
  public int persist(Process object) {
    return 0;
  }

  @Override
  public Process getLast() {
    return null;
  }

  @Override
  public ArrayList<Process> getAll() {
    return null;
  }

  public ArrayList<Process> getMachineProcesses(int machineId) {
    try {
      return Database.select("SELECT * FROM process WHERE"
          + " process.machine_id=" + machineId, (result) -> {
        ArrayList<Process> processes = new ArrayList<>();
        while (result.next()) {
          String programName = result.getString("program_name");
          Integer processId = result.getInt("process_id");
          Integer duration = result.getInt("duration");

          Process process = new Process(processId, programName, duration);
          processes.add(process);
        }
        return processes;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      return null;
    }
  }
}
