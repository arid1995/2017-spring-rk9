package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.devices.Robot;

public class RobotDao implements Dao<Robot> {

  private static AtomicInteger idGenerator = new AtomicInteger(-1);

  @Override
  public int persist(Robot robot) {
    try {
      int deviceId = Utils.getNextDeviceId();
      int robotId = Utils.getNextId("robot", "robot_id", idGenerator);
      String robotTableQuery = "INSERT INTO robot (robot_id, device_id) values("
          + robotId + "," + deviceId + ')';
      String deviceTableQuery =
          "INSERT INTO device (device_id, device_string_id, device_name) values("
              + deviceId + ",'" + robot.getDeviceStringId() + "','" + robot.getDeviceName() + "')";

      Database.update(robotTableQuery);
      Database.update(deviceTableQuery);
      return robotId;
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
      return -1;
    }
  }

  @Override
  public Robot getLast() {
    return null;
  }

  @Override
  public ArrayList<Robot> getAll() {
    try {
      return Database.select("SELECT r.robot_id rrid, d.device_id ddid, d.device_string_id ddsid,"
          + " d.device_name ddn"
          + " FROM robot r INNER JOIN device d"
          + " ON r.device_id=d.device_id", (result) -> {
        ArrayList<Robot> robots = new ArrayList<>();

        while (result.next()) {
          Integer robotId = result.getInt("rrid");
          Integer deviceId = result.getInt("ddid");
          String deviceStringId = result.getString("ddsid");
          String deviceName = result.getString("ddn");
          Robot robot = new Robot(deviceId, deviceStringId, deviceName, robotId);
          robots.add(robot);
        }
        return robots;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      return null;
    }
  }
}
