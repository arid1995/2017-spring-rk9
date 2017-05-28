package ru.bmstu.rk9.mechanics.models;

/**
 * Created by farid on 5/2/17.
 */
public class Stacker extends Device {

  public Stacker(Integer deviceId, Integer state) {
    super(deviceId, "saf", "asf");
  }

  public void putPalletOnConveyor(Pallet pallet) {
    pallet.removeFromOrder();
    sendMessageToDevice();
  }

  public void takePalletFromConveyor(Billet billet) {

  }
}
