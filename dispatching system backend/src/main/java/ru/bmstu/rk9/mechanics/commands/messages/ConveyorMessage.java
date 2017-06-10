package ru.bmstu.rk9.mechanics.commands.messages;

public class ConveyorMessage extends Message {

  private Integer palletId = 0;
  private Integer destinationKey = 0;

  public void movePalletToKey(int palletId, int destinationKey) {
    this.palletId = palletId;
    this.destinationKey = destinationKey;
  }

  public Integer getPalletId() {
    return palletId;
  }

  public Integer getDestinationKey() {
    return destinationKey;
  }
}

