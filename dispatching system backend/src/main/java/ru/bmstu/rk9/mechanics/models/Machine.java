package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by farid on 5/2/17.
 */
public class Machine extends Device {
  public static final String MILLING_TYPE = "Milling";
  public static final String LATHE_TYPE = "Lathe";

  private Integer machineId;
  private String machineType;
  private ArrayList<Process> technicalProcesses = new ArrayList<>();

  public Machine(Integer deviceId, String deviceStringId, String deviceName,
      Integer machineId, String machineType,
      ArrayList<Process> technicalProcesses) {
    super(deviceId, deviceStringId, deviceName);
    this.machineId = machineId;
    this.machineType = machineType;
    this.technicalProcesses = technicalProcesses;
  }

  public Machine(Integer deviceId, String deviceStringId, String deviceName, Integer state,
      Integer machineId, String machineType,
      ArrayList<Process> technicalProcesses) {
    super(deviceId, deviceStringId, deviceName, state);
    this.machineId = machineId;
    this.machineType = machineType;
    this.technicalProcesses = technicalProcesses;
  }

  public Integer getMachineId() {
    return machineId;
  }

  public String getMachineType() {
    return machineType;
  }

  public ArrayList<Process> getTechnicalProcesses() {
    return technicalProcesses;
  }

  public void startProcess(Billet billet) {

  }

  private void openCollet() {

  }

  private void closeCollet() {

  }
}
