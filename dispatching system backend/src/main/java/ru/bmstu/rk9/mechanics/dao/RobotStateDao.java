package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.Robot;
import ru.bmstu.rk9.mechanics.models.Stacker;
import ru.bmstu.rk9.mechanics.models.SystemState;

/**
 * Created by farid on 5/12/17.
 */
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

          Robot robot = new Robot(robotId, deviceId, deviceStringId, deviceName, robotState);
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
  public void persist(Robot object) {

  }

  @Override
  public Robot getLast() {
    return null;
  }
}
