package ru.bmstu.rk9.mechanics.schedule;

import java.util.ArrayList;
import ru.bmstu.rk9.mechanics.models.Billet;
import ru.bmstu.rk9.mechanics.models.Order;
import ru.bmstu.rk9.mechanics.models.Pallet;
import ru.bmstu.rk9.mechanics.models.Stock;

public class Scheduler {

  public void schedulePalletsInStock(ArrayList<Order> orders, Stock stock) {
    try {
      for (Order order : orders) {
        for (int i = 0; i < order.getRemained(); i += 2) {
          Pallet pallet = stock.getPalletAt(i / 2);
          if (pallet == null) {
            throw new IndexOutOfBoundsException();
          }

          for (int j = i; j < i + 2 && j < order.getRemained(); j++) {
            Billet billet = new Billet(order.getDetail(), pallet.getId());
            pallet.putBillet(billet);
          }
          stock.setPalletAt(i / 2, pallet);
        }
      }
    } catch (IndexOutOfBoundsException ignore) {
    }
  }
}
