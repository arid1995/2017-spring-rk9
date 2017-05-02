package ru.bmstu.rk9.dao;

import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.utils.DateUtils;

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
    private byte stacker;
    private byte key1;
    private byte key2;
    private byte key3;
    private byte key4;
    private byte collet1;
    private byte collet2;
    private Timestamp created;

    @Override
    public void persist() throws SQLException {
        StringBuilder query = new StringBuilder();
        created = new Timestamp(System.currentTimeMillis());
        query.append("INSERT INTO statelogger (machine_1, machine_2, robot_1, robot_2, stacker,")
                .append("key_1, key_2, key_3, key_4, collet_1, collet_2, created) VALUES(")
                .append(machine1).append(',')
                .append(machine2).append(',')
                .append(robot1).append(',')
                .append(robot2).append(',')
                .append(stacker).append(',')
                .append(key1).append(',')
                .append(key2).append(',')
                .append(key3).append(',')
                .append(key4).append(',')
                .append(collet1).append(',')
                .append(collet2).append(',')
                .append(created.getTime()).append(")");

        Database.update(query.toString());
    }

    @Override
    public void loadLast() throws SQLException {

        Database.select("SELECT TOP 1 * FROM statelogger ORDER BY id DESC", (result) -> {
            machine1 = result.getByte("machine_1");
            machine2 = result.getByte("machine_2");
            robot1 = result.getByte("robot_1");
            robot2 = result.getByte("robot_2");
            stacker = result.getByte("stacker");
            key1 = result.getByte("key_1");
            key2 = result.getByte("key_2");
            key3 = result.getByte("key_3");
            key4 = result.getByte("key_4");
            collet1 = result.getByte("collet_1");
            collet2 = result.getByte("collet_2");
            created = result.getTimestamp("created");
        });
    }

    public byte getMachine1() {
        return machine1;
    }

    public byte getMachine2() {
        return machine2;
    }

    public byte getRobot1() {
        return robot1;
    }

    public byte getRobot2() {
        return robot2;
    }

    public byte getStacker() {
        return stacker;
    }

    public byte getKey1() {
        return key1;
    }

    public byte getKey2() {
        return key2;
    }

    public byte getKey3() {
        return key3;
    }

    public byte getKey4() {
        return key4;
    }

    public byte getCollet1() {
        return collet1;
    }

    public byte getCollet2() {
        return collet2;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setMachine1(byte machine1) {
        this.machine1 = machine1;
    }

    public void setMachine2(byte machine2) {
        this.machine2 = machine2;
    }

    public void setRobot1(byte robot1) {
        this.robot1 = robot1;
    }

    public void setRobot2(byte robot2) {
        this.robot2 = robot2;
    }

    public void setStacker(byte stacker) {
        this.stacker = stacker;
    }

    public void setKey1(byte key1) {
        this.key1 = key1;
    }

    public void setKey2(byte key2) {
        this.key2 = key2;
    }

    public void setKey3(byte key3) {
        this.key3 = key3;
    }

    public void setKey4(byte key4) {
        this.key4 = key4;
    }

    public void setCollet1(byte collet1) {
        this.collet1 = collet1;
    }

    public void setCollet2(byte collet2) {
        this.collet2 = collet2;
    }
}
