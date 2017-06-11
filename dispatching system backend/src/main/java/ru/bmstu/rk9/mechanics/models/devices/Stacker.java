package ru.bmstu.rk9.mechanics.models.devices;

import ru.bmstu.rk9.mechanics.commands.RobotCommand;
import ru.bmstu.rk9.mechanics.commands.StackerCommand;
import ru.bmstu.rk9.mechanics.commands.messages.RobotMessage;
import ru.bmstu.rk9.mechanics.commands.messages.StackerMessage;
import ru.bmstu.rk9.mechanics.models.Billet;
import ru.bmstu.rk9.mechanics.models.Pallet;

public class Stacker extends Device {

  public Stacker(Integer deviceId, Integer state) {
    super(deviceId, "f4812d51-d523-4b9b-a1a7-bdc501d37104", "stacker");
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
    transaction = () -> {
      //TODO: think what to do after
    };
  }

  public void takeFromTheCell(int cellNumber) {
    StackerMessage message = new StackerMessage();
    message.takeFromTheCell(cellNumber);
    StackerCommand command = new StackerCommand();
    sendMessageToDevice(command);
    transaction = () -> {
      //TODO: think what to do after
    };
  }
}
