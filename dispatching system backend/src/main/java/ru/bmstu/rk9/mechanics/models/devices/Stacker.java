package ru.bmstu.rk9.mechanics.models.devices;

import ru.bmstu.rk9.mechanics.commands.StackerCommand;
import ru.bmstu.rk9.mechanics.commands.messages.StackerMessage;
import ru.bmstu.rk9.mechanics.models.Pallet;
import ru.bmstu.rk9.mechanics.models.Stock;

public class Stacker extends Device {
  private Pallet pallet = null;

  public static final int FREE = 0;
  public static final int BUSY = 1;

  public Stacker(Integer deviceId, Integer state) {
    super(deviceId, "f4812d51-d523-4b9b-a1a7-bdc501d37104", "stacker");
    state = FREE;
  }

  public void putPalletOnConveyor(Pallet pallet) {
    StackerMessage message = new StackerMessage();
    sendMessageToDevice(new StackerCommand(message));
    transaction = pallet::removeFromOrder;
  }

  public void putInTheCell(Pallet pallet, int cellNumber) {
    StackerMessage message = new StackerMessage();
    message.putInTheCell(cellNumber);
    StackerCommand command = new StackerCommand();
    sendMessageToDevice(command);
    state = BUSY;
    transaction = () -> {
      state = FREE;
    };
  }

  public void takeFromTheCell(int cellNumber, Stock stock) {
    StackerMessage message = new StackerMessage();
    message.takeFromTheCell(cellNumber);
    StackerCommand command = new StackerCommand();
    sendMessageToDevice(command);
    pallet = stock.yieldPalletAt(cellNumber);
    state = BUSY;
    transaction = () -> {
      state = FREE;
    };
  }

  public Pallet yieldPallet() {
    Pallet pallet = this.pallet;
    this.pallet = null;
    return pallet;
  }
}
