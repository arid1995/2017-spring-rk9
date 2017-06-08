package ru.bmstu.rk9.network.entities;

public class FeedbackMessage {
  private long timestamp;
  private int status;
  private String deviceId;

  public long getTimestamp() {
    return timestamp;
  }

  public int getStatus() {
    return status;
  }

  public String getDeviceId() {
    return deviceId;
  }
}
