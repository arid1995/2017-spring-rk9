package ru.bmstu.rk9.mechanics.models.devices;

import java.util.HashMap;
import ru.bmstu.rk9.mechanics.commands.Command;
import ru.bmstu.rk9.mechanics.commands.ConveyorCommand;
import ru.bmstu.rk9.mechanics.commands.messages.ConveyorMessage;
import ru.bmstu.rk9.mechanics.models.Pallet;

public class Conveyor extends Device {
  private HashMap<Integer, Pallet> pallets = new HashMap<>();

  public Conveyor(Integer deviceId, String deviceStringId, String deviceName, Integer state) {
    super(deviceId, deviceStringId, deviceName, state);
  }

  public void movePallet(int palletId, int keyNumber) {
    Pallet pallet;
  }

  //TODO: Think about multiple requests for move
  public void transportPallet(Pallet pallet, Integer key, Runnable nextAction) {
    ConveyorMessage message = new ConveyorMessage();
    message.movePalletToKey(pallet.getId(), key);
    ConveyorCommand command = new ConveyorCommand(message);
    transaction = nextAction;
  }

  public void putPallet(Pallet pallet) {
    transaction = () -> {
      pallets.put(pallet.getId(), pallet);
    };
  }

  public void takePallet() {

  }
}
