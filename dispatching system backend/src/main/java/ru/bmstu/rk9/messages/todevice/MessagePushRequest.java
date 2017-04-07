package ru.bmstu.rk9.messages.todevice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import ru.bmstu.rk9.messages.Message;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by farid on 4/7/17.
 */
@SuppressWarnings("FieldCanBeLocal")
public class MessagePushRequest {
    private static final ObjectMapper mapper = new ObjectMapper();

    //Change to ws when testing real devices
    private final String method = "http";
    private final String sender = "My IoT application";
    private String messageType;
    private ArrayList<? extends Message> messages;

    public MessagePushRequest(String messageType) {
        this.messageType = messageType;
    }

    private String toJson() {
        try {
            String st = mapper.writeValueAsString(this);
            return st;
        } catch (JsonProcessingException ex) {
            Logger log = Logger.getLogger(Logger.class.getName());
            log.log(Level.WARNING, ex.getMessage());
            return "err";
        }
    }

    public void push(String device, ArrayList<? extends Message> messages) {
        try {
            this.messages = messages;
            HttpResponse<JsonNode> jsonResponse =
                    Unirest.post("https://iotmmsp1942516588trial.hanatrial.ondemand.com/com.sap.iotservices.mms/v1/api/http/push/" + device)
                    .basicAuth("arid1995@mail.ru", "Tank1995")
                    .header("Content-Type", "application/json; charset=utf-8")
                    .body(this.toJson())
                    .asJson();

            System.out.println(jsonResponse.getBody().toString());
        } catch (UnirestException ex) {
            ex.printStackTrace();
        }
    }

    public String getMethod() {
        return method;
    }

    public String getSender() {
        return sender;
    }

    public String getMessageType() {
        return messageType;
    }

    public ArrayList<? extends Message> getMessages() {
        return messages;
    }
}
