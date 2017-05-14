package ru.bmstu.rk9.mechanics.schedule;

import java.util.ArrayList;
import ru.bmstu.rk9.mechanics.models.Billet;
import ru.bmstu.rk9.mechanics.models.Detail;
import ru.bmstu.rk9.mechanics.models.Order;
import ru.bmstu.rk9.mechanics.models.Pallet;

/**
 * Created by farid on 5/15/17.
 */
public class Scheduler {

  public ArrayList<Pallet> makePalletsFromOrders(ArrayList<Order> orders) {
    ArrayList<Pallet> pallets = new ArrayList<>();
    for (Order order : orders) {
      for (int i = 0; i < order.getRemained(); i += 2) {
        Pallet pallet = new Pallet(2);
        for (int j = i; j < i + 2 && j < order.getRemained(); j++) {
          Billet billet = new Billet(order.getDetail());
          pallet.putBillet(billet);
        }
        pallets.add(pallet);
      }
    }
    return pallets;
  }
}
