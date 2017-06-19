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
    Pallet pallet = pallets.get(palletId);
    ConveyorMessage message = new ConveyorMessage();
    message.movePalletToKey(palletId, keyNumber);
    ConveyorCommand command = new ConveyorCommand(message);
    sendMessageToDevice(command);
    pallet.setKey(keyNumber);
    return;
  }

  public void takeBilletOnKey(Billet billet, int key) {
    for (HashMap.Entry<Integer, Pallet> palletEntry : pallets.entrySet()) {
      if (palletEntry.getValue().getKey() == key) {
        palletEntry.getValue().takeBillet(billet);
        return;
      }
    }
  }

  public Billet yieldBilletOnKey(int key) {
    for (HashMap.Entry<Integer, Pallet> palletEntry : pallets.entrySet()) {
      if (palletEntry.getValue().getKey() == key) {
        //TODO: FUCK THAT REAL HARD!!!
        Billet billet = palletEntry.getValue().yieldBillet(0);
        if (billet == null) {
          return palletEntry.getValue().yieldBillet(1);
        }
        return billet;
      }
    }
    return null;
  }

  public void takePallet(Pallet pallet) {
      pallets.put(pallet.getId(), pallet);
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
}
