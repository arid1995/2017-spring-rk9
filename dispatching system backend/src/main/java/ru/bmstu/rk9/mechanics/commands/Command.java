package ru.bmstu.rk9.mechanics.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by farid on 4/21/17.
 */
public abstract class Command {
    private final String method = "http";
    private final String sender = "My IoT application";

    private static final ObjectMapper mapper = new ObjectMapper();
    public String toJson() {
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
