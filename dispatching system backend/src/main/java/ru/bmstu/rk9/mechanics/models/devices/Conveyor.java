package ru.bmstu.rk9.mechanics.models.devices;

import java.util.HashMap;
import ru.bmstu.rk9.mechanics.commands.ConveyorCommand;
import ru.bmstu.rk9.mechanics.commands.messages.ConveyorMessage;
import ru.bmstu.rk9.mechanics.models.Billet;
import ru.bmstu.rk9.mechanics.models.Pallet;
import ru.bmstu.rk9.mechanics.models.Process;

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

  public boolean hasPallets() {
    return !pallets.isEmpty();
  }

  public Pallet findMatchingPallet(Machine machine) {
    for (HashMap.Entry<Integer, Pallet> palletEntry : pallets.entrySet()) {
      Pallet pallet = palletEntry.getValue();
      for (HashMap.Entry<Integer, Billet> billetEntry : pallet.getBillets().entrySet()) {
        Process nextProcess = billetEntry.getValue().getNextProcess();
        if (machine.hasProgram(nextProcess.getProgramName())) {
          return pallet;
        }
      }
    }
    return null;
  }

  public void takePallet() {

  }
}
