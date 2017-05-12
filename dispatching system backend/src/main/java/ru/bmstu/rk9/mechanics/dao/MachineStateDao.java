package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Process;
import ru.bmstu.rk9.mechanics.models.Robot;

/**
 * Created by farid on 5/12/17.
 */
public class MachineStateDao implements Dao<Machine> {

  public ArrayList<Machine> getByStateId(int id) {
    try {
      return Database.select("SELECT m.machine_id mid, m.machine_type mmt,"
          + " ms.machine_state msms, d.device_id ddi, d.device_string_id ddsid, d.device_name ddn"
          + " FROM machine AS m INNER JOIN machine_state AS msms"
          + " ON ms.id=m.id INNER JOIN device AS d"
          + " ON m.device_id=d.device_id WHERE ms.state_id=" + id, (result) -> {
        ArrayList<Machine> machines = new ArrayList<>();
        while (result.next()) {
          int machineId = result.getInt("mid");
          String machineType = result.getString("mmt");
          int deviceId = result.getInt("ddi");
          String deviceStringId = result.getString("ddsid");
          String deviceName = result.getString("ddn");
          int machineState = result.getInt("msms");

          ArrayList<Process> processes = Database.select("SELECT * FROM process WHERE"
              + " process.machine_id=" + machineId, (procResult) -> {
            ArrayList<Process> processes1 = new ArrayList<>();
            while (procResult.next()) {
              String programName = procResult.getString("program_name");
              int processId = procResult.getInt("process_id");

              Process process = new Process(programName, processId);
              processes1.add(process);
            }
            return processes1;
          });

          Machine machine = new Machine(deviceId, deviceStringId, deviceName, machineState,
              machineId, machineType, processes
          );
          machines.add(machine);
        }

        return machines;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
    }

    return null;
  }

  @Override
  public void persist(Machine object) {

  }

  @Override
  public Machine getLast() {
    return null;
  }
}
