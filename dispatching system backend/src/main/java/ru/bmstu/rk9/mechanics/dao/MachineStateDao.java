package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Process;

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

          ArrayList<Process> processes = new ProcessDao().getByMachineId(machineId);

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
  public int persist(Machine machine) {
    String query = "INSERT INTO machine_state (state_id, machine_id, machine_state) values("
        + machine.getStateId() + ',' + machine.getMachineId() + ',' + machine.getState() + ')';
    try {
      Database.update(query);
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return -1;
  }

  @Override
  public Machine getLast() {
    return null;
  }

  @Override
  public ArrayList<Machine> getAll() {
    return null;
  }
}
