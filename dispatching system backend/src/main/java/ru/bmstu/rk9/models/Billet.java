package ru.bmstu.rk9.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by farid on 5/2/17.
 */
public class Billet {
    private TechnicalProcess process;
    private Integer nextMachine;

    Billet(Integer technicalProcessId) {
        process = new TechnicalProcess(technicalProcessId);
    }

    public Integer getNextMachine() {
        return process.machines.poll();
    }

    private class TechnicalProcess {
        int processId;
        LinkedList<Integer> machines = new LinkedList<>();

        TechnicalProcess(int processId) {
            this.processId = processId;
        }
    }
}
