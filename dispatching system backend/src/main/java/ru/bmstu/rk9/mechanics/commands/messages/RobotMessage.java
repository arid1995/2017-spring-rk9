package ru.bmstu.rk9.mechanics.commands.messages;

public class RobotMessage extends Message {
  private static final int TAKE_BILLET_FROM_PALLET = 0;
  private static final int PUT_BILLET_ON_MACHINE = 1;
  private static final int TAKE_BILLET_FROM_MACHINE = 2;
  private static final int PUT_BILLET_ON_PALLET = 3;

  private Integer action = 0;

  public Integer getAction() {
    return action;
  }

  public void takeBilletFromPallet() {
    action = 0;
  }

  public void putBilletInMachine() {
    action = 1;
  }

  public void takeBilletFromMachine() {
    action = 2;
  }

  public void putBilletOnPallet() {
    action = 3;
  }
}
