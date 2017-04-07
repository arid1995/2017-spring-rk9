package ru.bmstu.rk9.messages.fromdevice;

import java.util.ArrayList;

/**
 * Created by farid on 4/7/17.
 */
public abstract class IncomingRequest<T> {
    protected String mode;
    protected String messageType;
    protected ArrayList<T> messages;

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
