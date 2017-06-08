package ru.bmstu.rk9.mechanics.commands.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Message {
  private long timestamp;
  private static final ObjectMapper message = new ObjectMapper();

  public Message() {
    timestamp = System.currentTimeMillis() / 1000;
  }

  public long getTimestamp() {
    return timestamp;
  }

  public String toJson() {
    try {
      return message.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}