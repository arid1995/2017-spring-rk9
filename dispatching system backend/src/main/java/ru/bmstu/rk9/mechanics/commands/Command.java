package ru.bmstu.rk9.mechanics.commands;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;

public abstract class Command <T> {
    private final String method = "http";
    private final String sender = "My IoT application";
    public ArrayList<T> messages = new ArrayList<>();

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
