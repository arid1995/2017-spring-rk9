package ru.bmstu.rk9.mechanics.schedule;

import ru.bmstu.rk9.mechanics.dao.Dao;
import ru.bmstu.rk9.mechanics.dao.OrderDao;
import ru.bmstu.rk9.mechanics.dao.SystemStateDao;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Order;
import ru.bmstu.rk9.mechanics.models.Pallet;
import ru.bmstu.rk9.mechanics.models.Robot;
import ru.bmstu.rk9.mechanics.models.Stacker;
import ru.bmstu.rk9.mechanics.models.Stock;
import ru.bmstu.rk9.mechanics.models.SystemState;

import java.util.ArrayList;

/**
 * Created by farid on 4/29/17.
 */
public class Dispatcher {

  private boolean isWorking = false;
  private final ArrayList<Machine> machines;
  private final ArrayList<Robot> robots;
  //private final Conveyor conveyor;
  private final Stacker stacker;
  private final Stock stock;
  private final SystemState systemState;
  private ArrayList<Order> orders = new ArrayList<>();
  Scheduler scheduler = new Scheduler();

  private final Dao<SystemState> systemStateDao = new SystemStateDao();

  public Dispatcher() {
    systemState = systemStateDao.getLast();
    robots = systemState.getRobotStates();
    machines = systemState.getMachineStates();
    stacker = new Stacker(0, 0);
    int STOCK_CAPACITY = 20;
    stock = new Stock(STOCK_CAPACITY, STOCK_CAPACITY);
    orders = new OrderDao().getUnfinishedOrders();
    reschedule();
    start();
  }

  public void addTask(Order order) {
    orders.add(order);
    reschedule();
  }

  public ArrayList<Order> getOrders() {
    return new ArrayList<>(orders);
  }

  public void save() {

  }

  private void start() {
    stacker.putPalletOnConveyor(stock.getNextPallet());
  }

  private void reschedule() {
    scheduler.schedulePalletsInStock(orders, stock);
  }
}