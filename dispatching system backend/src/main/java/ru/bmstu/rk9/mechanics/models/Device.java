package ru.bmstu.rk9.mechanics.models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by farid on 5/2/17.
 */
public class Device {
  protected Integer deviceId;
  protected String deviceUrl;
  protected String deviceStringId;
  protected String deviceName;
  protected Integer state;
  protected Integer stateId;

  public Device(String deviceStringId, String deviceName) {
    this.deviceStringId = deviceStringId;
    this.deviceName = deviceName;
  }

  public Device(Integer deviceId, String deviceStringId, String deviceName) {
    this.deviceId = deviceId;
    this.deviceStringId = deviceStringId;
    this.deviceName = deviceName;
  }

  public Device(Integer deviceId, String deviceStringId, String deviceName, Integer state) {
    this.deviceStringId = deviceStringId;
    this.deviceName = deviceName;
    this.state = state;
  }



  public void sendMessageToDevice() {

  }

  public String getDeviceStringId() {
    return deviceStringId;
  }

  public String getDeviceName() {
    return deviceName;
  }

  public Integer getState() {
    return state;
  }

  public Integer getStateId() {
    return stateId;
  }

  public void setDeviceId(Integer deviceId) {
    this.deviceId = deviceId;
  }

  public void setDeviceUrl(String deviceUrl) {
    this.deviceUrl = deviceUrl;
  }

  public void setDeviceStringId(String deviceStringId) {
    this.deviceStringId = deviceStringId;
  }

  public void setDeviceName(String deviceName) {
    this.deviceName = deviceName;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public void setStateId(Integer stateId) {
    this.stateId = stateId;
  }
}
