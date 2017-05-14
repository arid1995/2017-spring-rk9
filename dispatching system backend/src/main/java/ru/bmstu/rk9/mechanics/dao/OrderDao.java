package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.models.Detail;
import ru.bmstu.rk9.mechanics.models.Order;

/**
 * Created by farid on 5/11/17.
 */
public class OrderDao implements Dao<Order> {
  private static AtomicInteger idGenerator = new AtomicInteger(-1);

  @Override
  public int persist(Order order) {
    try {
      int nextId = Utils.getNextId("client_order", "order_id", idGenerator);
      order.setOrderId(nextId);
      Database.update("INSERT INTO client_order (order_id, detail_id, amount, remained) values("
          + nextId + "," + order.getDetail().getDetailId() + "," + order.getAmount() + ","
          + order.getRemained() + ")");
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
    }
    return 0;
  }

  public ArrayList<Order> getUnfinishedOrders() {
    try {
      return Database.select("SELECT * FROM client_order WHERE remained > 0", (result) -> {
        ArrayList<Order> orders = new ArrayList<>();
        while (result.next()) {
          int orderId = result.getInt("order_id");
          int detailId = result.getInt("detail_id");
          int amount = result.getInt("amount");
          int remained = result.getInt("remained");

          DetailDao detailDao = new DetailDao();
          Detail detail = detailDao.getById(detailId);
          orders.add(new Order(orderId, detail, remained, remained));
        }
        return orders;
      });
    } catch (SQLException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
      return null;
    }
  }

  @Override
  public Order getLast() {
    return null;
  }

  @Override
  public ArrayList<Order> getAll() {
    return null;
  }
}
