package ru.bmstu.rk9.messages.fromdevice;

import java.util.ArrayList;

/**
 * Created by farid on 4/7/17.
 */
public class IncomingRequest<T> {
    private String mode;
    private String messageType;
    private ArrayList<T> messages;

    public String getMode() {
        return mode;
    }

    public String getMessageType() {
        return messageType;
    }

    public ArrayList<T> getMessages() {
        return messages;
    }
}
