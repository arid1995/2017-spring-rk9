package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.Detail;
import ru.bmstu.rk9.mechanics.models.Process;

/**
 * Created by farid on 5/11/17.
 */
public class DetailDao implements Dao<Detail> {

  private static AtomicInteger idGenerator = new AtomicInteger(-1);

  @Override
  public int persist(Detail detail) {
    int detailId = 0;
    try {
      detailId = Utils.getNextId("detail", "detail_id", idGenerator);
      detail.setDetailId(detailId);
      String detailQuery = "INSERT INTO detail (detail_id, name) values("
          + detail.getDetailId() + ",'" + detail.getName() + "')";
      Database.update(detailQuery);

      int i = 1;
      for (Process process : detail.getProcesses()) {
        String processToDetail =
            "INSERT INTO process_to_detail (process_id, detail_id, process_order)"
                + "values(" + process.getProcessId() + "," + detailId + "," + i + ")";
        Database.update(processToDetail);
        i++;
      }
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
    }

    return detailId;
  }

  @Override
  public Detail getLast() {
    return null;
  }

  public Detail getById(int id) {
    try {
      return Database.select("SELECT * FROM detail AS d WHERE"
          + " d.detail_id=" + id, (result) -> {
        Detail detail = null;
        if (result.next()) {
          String name = result.getString("name");
          ProcessDao processDao = new ProcessDao();
          ArrayList<Process> processes = processDao.getByDetailId(id);

          detail = new Detail(id, name, processes);
        }
        return detail;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
      return null;
    }
  }

  @Override
  public ArrayList<Detail> getAll() {
    return null;
  }
}
