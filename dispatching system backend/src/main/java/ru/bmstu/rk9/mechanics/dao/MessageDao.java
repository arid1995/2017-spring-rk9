package ru.bmstu.rk9.mechanics.dao;

import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.mechanics.commands.Command;
import ru.bmstu.rk9.utils.DateUtils;

import java.sql.SQLException;

/**
 * Created by farid on 4/7/17.
 */
public class MessageDao implements Dao<Command> {

  //@Override
  public void persist(Command command) {

  }

  public Command getLast() {
    return null;
  }
}
