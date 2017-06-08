package ru.bmstu.rk9.network.entities;

import java.util.ArrayList;

public class FeedbackMessageEntity {
  private String messageType;
  private ArrayList<FeedbackMessage> messages;

  public String getMessageType() {
    return messageType;
  }

  public ArrayList<FeedbackMessage> getMessages() {
    return messages;
  }
}
