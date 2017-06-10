package ru.bmstu.rk9.mechanics.commands.messages;

public class StackerMessage extends Message {
  private static final int TAKE_BILLET_FROM_THE_STOCK = 0;
  private static final int PUT_BILLET_IN_THE_STOCK = 1;

  private Integer action = 0;
  private Integer cell = 0;

  public void takeFromTheCell(int cell) {
    action = 0;
    this.cell = cell;
  }

  public void putInTheCell(int cell) {
    action = 1;
    this.cell = cell;
  }

  public Integer getAction() {
    return action;
  }

  public Integer getCell() {
    return cell;
  }
}
