package ru.bmstu.rk9.mechanics.models;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import ru.bmstu.rk9.mechanics.commands.Command;

public abstract class Device {

  protected Integer deviceId;
  protected String deviceUrl;
  protected String deviceStringId;
  protected String deviceName;
  protected Integer state;
  protected Integer stateId;
  protected Runnable transaction;
  protected WebSocketSession session;

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

  public void sendMessageToDevice(Command command) {
    try {
      if (session != null) {
        session.sendMessage(new TextMessage(command.toJson()));
        System.out.println(command.toJson());
        return;
      }
      Logger.getLogger(Logger.class.getName())
          .log(Level.WARNING, "Websocket session is not initialized");
    } catch (IOException ex) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
    }
  }

  public void executeTransaction() {
    if (transaction != null) {
      transaction.run();
    }
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

  public void setSession(WebSocketSession session) {
    this.session = session;
  }
}
