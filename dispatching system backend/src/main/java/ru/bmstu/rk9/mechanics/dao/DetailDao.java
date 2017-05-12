package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.Detail;

/**
 * Created by farid on 5/11/17.
 */
public class DetailDao implements Dao<Detail>{
  private static AtomicInteger idGenerator = new AtomicInteger(-1);

  @Override
  public int persist(Detail object) {
    int detailId = 0;
    try {
      detailId = Utils.getNextId("detail", "detail_id", idGenerator);
      String query = "INSERT INTO detail () values(";
      Database.update(query);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return detailId;
  }

  @Override
  public Detail getLast() {
    return null;
  }

  @Override
  public ArrayList<Detail> getAll() {
    return null;
  }
}
