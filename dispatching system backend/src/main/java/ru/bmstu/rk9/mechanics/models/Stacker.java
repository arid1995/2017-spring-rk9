package ru.bmstu.rk9.mechanics.models;

import ru.bmstu.rk9.mechanics.commands.ConveyorCommand;

/**
 * Created by farid on 5/2/17.
 */
public class Stacker extends Device {

  public Stacker(Integer deviceId, Integer state) {
    super(deviceId, "saf", "asf");
  }

  public void putPalletOnConveyor(Pallet pallet) {
    transactions.push(() -> {
      pallet.removeFromOrder();
      sendMessageToDevice(new ConveyorCommand());
    });
  }

  public void takePalletFromConveyor(Billet billet) {

  }
}
