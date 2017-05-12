package ru.bmstu.rk9.mechanics.models;

import java.sql.Timestamp;

/**
 * Created by farid on 5/11/17.
 */
public class Message {
  private Integer messageId;
  private Timestamp created;
  private Integer status;
  private Integer deviceId;

  public Message(Timestamp created, Integer status, Integer deviceId) {
    this.created = created;
    this.status = status;
    this.deviceId = deviceId;
  }

  public Integer getMessageId() {
    return messageId;
  }

  public Timestamp getCreated() {
    return created;
  }

  public Integer getStatus() {
    return status;
  }

  public Integer getDeviceId() {
    return deviceId;
  }

  public void setMessageId(Integer messageId) {
    this.messageId = messageId;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public void setDeviceId(Integer deviceId) {
    this.deviceId = deviceId;
  }
}
