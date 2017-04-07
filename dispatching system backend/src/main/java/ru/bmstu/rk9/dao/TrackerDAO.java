package ru.bmstu.rk9.dao;

import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.utils.DateUtils;

import java.sql.SQLException;

/**
 * Created by farid on 4/7/17.
 */
public class TrackerDAO implements Dao {
    private String device;
    private String state;

    public TrackerDAO(String device, String state) {
        this.device = device;
        this.state = state;
    }

    public String getDevice() {
        return device;
    }

    public String getState() {
        return state;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void persist() throws SQLException {
        if (device != null && state != null) {
            Database.update("INSERT INTO TRACKER (created, device, state) VALUES(" +
                    DateUtils.getCurrentTimestamp() + ",'" +
                    device + "','" +
                    state +
                    "');");
        }
    }

    public void loadLast() {

    }
}
