package ru.bmstu.rk9.dao;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Created by farid on 4/21/17.
 */
public class SystemStateDAO implements Dao {
    private byte machine1;
    private byte machine2;
    private byte robot1;
    private byte robot2;
    private byte stacker1;
    private byte stacker2;
    private byte key1;
    private byte key2;
    private byte collet1;
    private byte collet2;
    private Timestamp created;

    @Override
    public void persist() throws SQLException {

    }

    @Override
    public void loadLast() throws SQLException {

    }
}
