package ru.bmstu.rk9.mechanics.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.Process;

public class ProcessDao implements Dao<Process> {

  private static AtomicInteger idGenerator = new AtomicInteger(-1);

  @Override
  public int persist(Process process) {
    try {
      int nextId = Utils.getNextId("process", "process_id", idGenerator);
      process.setProcessId(nextId);
      String query = "INSERT INTO process (process_id, program_name, duration) values("
          + nextId + ',' + "'" + process.getProgramName() + "'," + process.getDuration()
          + ");";
      Database.update(query);
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
    }
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

  public Process getById(int id) {
    try {
      return Database.select("SELECT * FROM process AS p WHERE"
          + " p.process_id=" + id, (result) -> {
        Process process = null;
        if (result.next()) {
          String programName = result.getString("program_name");
          Integer processId = result.getInt("process_id");
          Integer duration = result.getInt("duration");

          process = new Process(processId, programName, duration);
        }
        return process;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
      return null;
    }
  }

  private ArrayList<Process> fillProcessesFromResultSet(ResultSet result) throws SQLException {
    ArrayList<Process> processes = new ArrayList<>();
    while (result.next()) {
      String programName = result.getString("program_name");
      Integer processId = result.getInt("process_id");
      Integer duration = result.getInt("duration");

      processes.add(new Process(processId, programName, duration));
    }
    return processes;
  }

  public ArrayList<Process> getByDetailId(int detailId) {
    try {
      return Database.select("SELECT * FROM process AS p INNER JOIN process_to_detail AS ptd"
              + " ON p.process_id=ptd.process_id WHERE"
              + " ptd.detail_id=" + detailId + " ORDER BY ptd.process_order ASC",
          this::fillProcessesFromResultSet);
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      return null;
    }
  }

  public ArrayList<Process> getByMachineId(int machineId) {
    try {
      return Database.select("SELECT * FROM process AS p INNER JOIN machine_to_process AS mtp"
          + " ON p.process_id=mtp.process_id WHERE"
          + " mtp.machine_id=" + machineId, this::fillProcessesFromResultSet);
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      return null;
    }
  }
}
