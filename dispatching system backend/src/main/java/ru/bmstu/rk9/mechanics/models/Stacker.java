package ru.bmstu.rk9.mechanics.models;

import ru.bmstu.rk9.mechanics.commands.ConveyorCommand;
import ru.bmstu.rk9.mechanics.commands.StackerCommand;
import ru.bmstu.rk9.mechanics.commands.messages.StackerMessage;

public class Stacker extends Device {

  public Stacker(Integer deviceId, Integer state) {
    super(deviceId, "f4812d51-d523-4b9b-a1a7-bdc501d37104", "stacker");
  }

  public void putPalletOnConveyor(Pallet pallet) {
    StackerMessage message = new StackerMessage();
    sendMessageToDevice(new StackerCommand(message));
    transaction = pallet::removeFromOrder;
  }

  public void takePalletFromConveyor(Billet billet) {

  }
}
