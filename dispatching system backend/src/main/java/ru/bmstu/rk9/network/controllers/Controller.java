package ru.bmstu.rk9.network.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * Created by farid on 5/9/17.
 */
public abstract class Controller {
    private ObjectMapper mapper = new ObjectMapper();

    public static final String WRONG_PARAMETER_TYPE = "Wrong parameter type";

    protected String wrapErrorMessage(String message) throws JsonProcessingException {
        ObjectNode errorMessage = mapper.createObjectNode();
        errorMessage.put("message", message);

        return mapper.writeValueAsString(errorMessage);
    }
}
