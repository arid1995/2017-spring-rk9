package ru.bmstu.rk9.mechanics.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Command<T> {

  private String messageType;
  private ArrayList<T> messages;

  @SafeVarargs
  public Command(String messageType, T... messages) {
    this.messageType = messageType;
    this.messages = new ArrayList<>(Arrays.asList(messages));
  }

  private static final ObjectMapper mapper = new ObjectMapper();

  public String getMessageType() {
    return messageType;
  }

  public ArrayList<T> getMessages() {
    return messages;
  }

  public String toJson() {
    try {
      return mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}
