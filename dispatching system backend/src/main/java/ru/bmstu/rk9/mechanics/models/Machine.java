package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;

/**
 * Created by farid on 5/2/17.
 */
public class Machine extends Device {
    public static final String MILLING_TYPE = "Milling";
    public static final String LATHE_TYPE = "Lathe";

    private Integer machineId;
    private ArrayList<Process> technicalProcesses = new ArrayList<>();

    public void startProcess(Billet billet) {

    }

    private void openCollet() {

    }

    private void closeCollet() {

    }
}
