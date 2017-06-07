package ru.bmstu.rk9.mechanics.commands.messages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

/**
 * Created by farid on 4/7/17.
 */

public abstract class Message {

  public long getTimestamp() {
    long timestamp = System.currentTimeMillis() / 1000;
    return timestamp;
  }

  private static final ObjectMapper message = new ObjectMapper();

  public String toJson() {
    try {
      return message.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}