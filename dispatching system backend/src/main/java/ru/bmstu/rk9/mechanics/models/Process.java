package ru.bmstu.rk9.mechanics.models;

/**
 * Created by farid on 5/11/17.
 */
public class Process {
    private Integer processId;
    private String programName;
    private Integer duration;

    public Process(Integer processId, String programName, Integer duration) {
        this.processId = processId;
        this.programName = programName;
        this.duration = duration;
    }
}
