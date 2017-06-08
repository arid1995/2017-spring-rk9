package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicInteger;
import ru.bmstu.rk9.database.Database;

public class Utils {

  private static AtomicInteger idGenerator = new AtomicInteger(-1);

  static Integer getNextDeviceId() throws SQLException {
    return getNextId("device", "device_id", idGenerator);
  }

  static Integer getNextId(String tableName, String idName, AtomicInteger generator)
      throws SQLException {
    if (generator.get() == -1) {
      int id = Database.select("SELECT MAX(" + idName + ") AS mx from " + tableName, (result) -> {
        if (!result.next()) {
          return 0;
        }
        return result.getInt("mx");
      });
      generator.set(id);
    }

    return generator.incrementAndGet();
  }
}
