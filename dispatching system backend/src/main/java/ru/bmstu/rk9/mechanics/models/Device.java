package ru.bmstu.rk9.mechanics.models;

/**
 * Created by farid on 5/2/17.
 */
public abstract class Device {
    protected Integer deviceId;
    protected String deviceUrl;
    protected String deviceStringId;
    protected String deviceName;
    protected Integer state;

    public Device(Integer state) {
      this.state = state;
    }

    public Device(String deviceStringId, String deviceName, Integer state) {

    }

    public void sendMessageToDevice() {

    }
}
