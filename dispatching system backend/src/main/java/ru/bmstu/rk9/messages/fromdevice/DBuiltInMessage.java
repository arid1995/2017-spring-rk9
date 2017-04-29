package ru.bmstu.rk9.messages.fromdevice;

import ru.bmstu.rk9.messages.Message;

/**
 * Created by farid on 4/7/17.
 */

@SuppressWarnings("FieldCanBeLocal")
public class DBuiltInMessage extends Message {
    private String sensor;
    private String value;
    private String timestamp;

    public DBuiltInMessage() {
    }

    public DBuiltInMessage(String sensor, String value, String timestamp) {
        this.sensor = sensor;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getSensor() {
        return sensor;
    }

    public String getValue() {
        return value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
