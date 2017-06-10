package ru.bmstu.rk9.mechanics.commands.messages;

public class MachineMessage extends Message {
  private static final int OPEN_COLLET_ACTION = 0;
  private static final int CLOSE_COLLET_ACTION = 1;
  private Integer action = 0;

  public Integer getAction() {
    return action;
  }

  public void openCollet() {
    action = 0;
  }

  public void closeCollet() {
    action = 1;
  }
}
