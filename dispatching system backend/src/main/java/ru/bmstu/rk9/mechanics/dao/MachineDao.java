package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.devices.Machine;
import ru.bmstu.rk9.mechanics.models.Process;

public class MachineDao implements Dao<Machine> {

  private static AtomicInteger idGenerator = new AtomicInteger(-1);

  @Override
  public int persist(Machine machine) {
    try {
      int deviceId = Utils.getNextDeviceId();
      int machineId = Utils.getNextId("machine", "machine_id", idGenerator);

      String machineTableQuery = "INSERT INTO machine (machine_id, machine_type, device_id) values("
          + machineId + ",'" + machine.getMachineType() + "'," + deviceId + ')';
      String deviceTableQuery =
          "INSERT INTO device (device_id, device_string_id, device_name) values("
              + deviceId + ",'" + machine.getDeviceStringId() + "','" + machine.getDeviceName()
              + "')";

      Database.update(machineTableQuery);
      Database.update(deviceTableQuery);
      return machineId;
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
      return -1;
    }
  }

  @Override
  public Machine getLast() {
    return null;
  }

  public Machine getById(int id) {
    try {
      return Database.select("SELECT m.machine_id mmid, m.machine_type mmt, d.device_id ddid,"
          + " d.device_string_id ddsid, d.device_name ddn"
          + " FROM machine m INNER JOIN device d"
          + " ON m.device_id=d.device_id WHERE m.machine_id=" + id, (result) -> {
        Machine machine = null;
        if (result.next()) {
          Integer machineId = result.getInt("mmid");
          String machineType = result.getString("mmt");
          Integer deviceId = result.getInt("ddid");
          String deviceStringId = result.getString("ddsid");
          String deviceName = result.getString("ddn");

          machine = new Machine(deviceId, deviceStringId, deviceName, machineId,
              machineType, null
          );
        }
        return machine;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      return null;
    }
  }

  @Override
  public ArrayList<Machine> getAll() {
    try {
      return Database.select("SELECT m.machine_id mmid, m.machine_type mmt, d.device_id ddid,"
          + " d.device_string_id ddsid, d.device_name ddn"
          + " FROM machine m INNER JOIN device d"
          + " ON m.device_id=d.device_id", (result) -> {
        ArrayList<Machine> machines = new ArrayList<>();

        while (result.next()) {
          Integer machineId = result.getInt("mmid");
          String machineType = result.getString("mmt");
          Integer deviceId = result.getInt("ddid");
          String deviceStringId = result.getString("ddsid");
          String deviceName = result.getString("ddn");

          HashMap<String, Process> processes = new ProcessDao().getByMachineId(machineId);

          Machine machine = new Machine(deviceId, deviceStringId, deviceName, machineId,
              machineType, processes
          );
          machines.add(machine);
        }
        return machines;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      return null;
    }
  }

  public void addProcessToMachine(Process process, Machine machine) {
    Integer processId = process.getProcessId();
    Integer machineId = machine.getMachineId();

    try {
      if (processId == null || machineId == null) {
        throw new SQLException("Process id or machine id must not be null");
      }
      Database.update("INSERT INTO machine_to_process (machine_id, process_id) values("
          + machineId + ',' + processId + ")");
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
    }
  }
}
