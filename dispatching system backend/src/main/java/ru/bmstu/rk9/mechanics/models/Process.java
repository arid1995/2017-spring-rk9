package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;
import ru.bmstu.rk9.mechanics.models.devices.Machine;

public class Process {

  private Integer processId;
  private String programName;
  private Integer duration;
  private ArrayList<Machine> machines = new ArrayList<>();

  public Process(String programName, Integer duration) {
    this.programName = programName;
    this.duration = duration;
  }

  public Process(Integer processId, String programName, Integer duration) {
    this.processId = processId;
    this.programName = programName;
    this.duration = duration;
  }

  public Process(Integer processId, String programName, Integer duration,
      ArrayList<Machine> machines) {
    this.processId = processId;
    this.programName = programName;
    this.duration = duration;
    this.machines = machines;
  }

  public Integer getProcessId() {
    return processId;
  }

  public String getProgramName() {
    return programName;
  }

  public Integer getDuration() {
    return duration;
  }

  public ArrayList<Machine> getMachines() {
    return machines;
  }

  public void setProcessId(Integer processId) {
    this.processId = processId;
  }

  public void setProgramName(String programName) {
    this.programName = programName;
  }

  public void setDuration(Integer duration) {
    this.duration = duration;
  }
}
