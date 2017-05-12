package ru.bmstu.rk9.mechanics.models;

/**
 * Created by farid on 5/11/17.
 */
public class Process extends DbModel {
    private String programName;
    private Integer processId;

    public Process(String programName, Integer processId) {
        this.programName = programName;
        this.processId = processId;
    }

    @Override
    public int incrementAndGet() {
        return 0;
    }

    @Override
    public int getModelId() {
        return 0;
    }
}
