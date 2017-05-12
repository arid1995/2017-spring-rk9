package ru.bmstu.rk9.mechanics.dao;

import java.util.ArrayList;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.commands.Command;
import ru.bmstu.rk9.utils.DateUtils;

import java.sql.SQLException;

/**
 * Created by farid on 4/7/17.
 */
public class MessageDao implements Dao<Command> {

  //@Override
  public int persist(Command command) {
    return 0;
  }

  public Command getLast() {
    return null;
  }

  @Override
  public ArrayList<Command> getAll() {
    return null;
  }
}
