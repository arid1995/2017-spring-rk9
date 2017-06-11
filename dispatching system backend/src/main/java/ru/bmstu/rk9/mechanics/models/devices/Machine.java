package ru.bmstu.rk9.mechanics.models.devices;

import java.util.ArrayList;
import ru.bmstu.rk9.mechanics.commands.MachineCommand;
import ru.bmstu.rk9.mechanics.commands.MachineProcessCommand;
import ru.bmstu.rk9.mechanics.commands.messages.MachineMessage;
import ru.bmstu.rk9.mechanics.commands.messages.MachineProcessMessage;
import ru.bmstu.rk9.mechanics.models.Billet;
import ru.bmstu.rk9.mechanics.models.Process;

public class Machine extends Device {

  public static final String MILLING_TYPE = "Milling";
  public static final String LATHE_TYPE = "Lathe";

  public static final int FREE = 0;
  public static final int BUSY = 1;
  public static final int FINISHED = 2;

  private Integer machineId;
  private String machineType;
  private ArrayList<Process> technicalProcesses = new ArrayList<>();

  public Machine(String deviceStringId, String deviceName, String machineType) {
    super(deviceStringId, deviceName);
    this.machineType = machineType;
  }

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

  public void setMachineId(Integer machineId) {
    this.machineId = machineId;
  }

  public void setTechnicalProcesses(
      ArrayList<Process> technicalProcesses) {
    this.technicalProcesses = technicalProcesses;
  }

  public void startProcess(Billet billet) {
    MachineProcessMessage message = new MachineProcessMessage();
    message.setProgramName(billet.getProcesses().remove(0).getProgramName());
    MachineProcessCommand command = new MachineProcessCommand(message);
    sendMessageToDevice(command);
    transaction = () -> {
      state = FINISHED;
    };
  }

  private void openCollet() {
    MachineMessage message = new MachineMessage();
    message.openCollet();
    MachineCommand command = new MachineCommand(message);
    sendMessageToDevice(command);
    transaction = () -> {
      state = FREE;
    };
  }

  private void closeCollet() {
    MachineMessage message = new MachineMessage();
    message.closeCollet();
    MachineCommand command = new MachineCommand(message);
    sendMessageToDevice(command);
    transaction = () -> {
      state = BUSY;
    };
  }
}
