package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.devices.Robot;

public class RobotStateDao implements Dao<Robot> {
  public ArrayList<Robot> getByStateId(int id) {
    try {
      return Database.select("SELECT r.robot_id rid, rs.robot_state rsrs, d.device_id ddi,"
          + " d.device_string_id ddsid, device.device_name ddn,"
          + " FROM robot AS r INNER JOIN robot_state AS rs"
          + " ON robot_state.id=robot.id INNER JOIN device AS d"
          + " ON robot.device_id=device.device_id WHERE robot_state.state_id=" + id, (result) -> {
        ArrayList<Robot> robots = new ArrayList<>();
        while (result.next()) {
          int robotId = result.getInt("rid");
          int deviceId = result.getInt("ddi");
          String deviceStringId = result.getString("ddsid");
          String deviceName = result.getString("ddn");
          int robotState = result.getInt("rsrs");

          Robot robot = new Robot(deviceId, deviceStringId, deviceName, robotState, robotId);
          robots.add(robot);
        }

        return robots;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
    }

    return null;
  }

  @Override
  public int persist(Robot robot) {
    String query = "INSERT INTO robot_state (state_id, robot_id, robot_state) values("
        + robot.getStateId() + ',' + robot.getRobotId() + ',' + robot.getState() + ')';
    try {
      Database.update(query);
      return -1;
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
    return null;
  }
}
