package ru.bmstu.rk9.mechanics.models;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import ru.bmstu.rk9.mechanics.commands.Command;

/**
 * Created by farid on 5/2/17.
 */

public abstract class Device {

  protected Integer deviceId;
  protected String deviceUrl;
  protected String deviceStringId;
  protected String deviceName;
  protected Integer state;
  protected Integer stateId;
  protected Runnable transaction;

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
      HttpResponse<JsonNode> jsonResponse =
          Unirest.post(
              "https://iotmmsp1942516588trial.hanatrial.ondemand.com/com.sap.iotservices.mms/v1/api/http/push/"
                  + deviceStringId)
              .basicAuth("arid1995@mail.ru", "Tank1995")
              .header("Content-Type", "application/json; charset=utf-8")
              .body(command.toJson())
              .asJson();

      System.out.println(jsonResponse.getBody().toString());
      System.out.println(command.toJson());
    } catch (UnirestException ex) {
      Logger.getLogger(Logger.class.getName()).log(Level.WARNING, ex.getMessage(), ex);
    }
  }

  public void executeTransaction() {
    if (transaction != null) {
      transaction.run();
    }
  }

  public void actionReady() {
    executeTransaction();
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
