package ru.bmstu.rk9.mechanics.dao;

import ru.bmstu.rk9.mechanics.models.Machine;

/**
 * Created by farid on 5/11/17.
 */
public class MachineDao implements Dao<Machine> {

  @Override
  public void persist(Machine object) {

  }

  @Override
  public Machine getLast() {
    return null;
  }
}
