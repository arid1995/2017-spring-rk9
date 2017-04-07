package ru.bmstu.rk9.models;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by farid on 4/7/17.
 */
@SuppressWarnings("FieldCanBeLocal")
public class MessagePushRequest {
    private static final transient Gson gson = new Gson();

    //Change to ws when testing real devices
    private final String method = "http";
    private final String sender = "My IoT application";
    private String messageType;
    private ArrayList<Message> messages;

    public MessagePushRequest(String messageType) {
        this.messageType = messageType;
    }

    private String toJson() {
        String st = gson.toJson(this);
        return st;
    }

    public void push(String device, Message... messages) {
        this.messages = new ArrayList<>(Arrays.asList(messages));
        try {
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
}
