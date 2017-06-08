package ru.bmstu.rk9.mechanics.dao;

import java.util.ArrayList;
import ru.bmstu.rk9.mechanics.commands.Command;

public class MessageDao implements Dao<Command> {

  @Override
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
