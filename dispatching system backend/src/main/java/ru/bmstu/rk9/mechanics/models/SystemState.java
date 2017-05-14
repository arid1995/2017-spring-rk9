package ru.bmstu.rk9.mechanics.models;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by farid on 5/11/17.
 */
public class SystemState {
  private Integer systemStateId;
  private Timestamp created;
  private Stacker stacker;
  private ArrayList<Robot> robotStates;
  private ArrayList<Machine> machineStates;

  public SystemState(int systemSateId, Timestamp created, Stacker stacker) {
    this.systemStateId = systemSateId;
    this.created = created;
    this.stacker = stacker;
  }

  public Timestamp getCreated() {
    return created;
  }

  public Stacker getStacker() {
    return stacker;
  }

  public ArrayList<Robot> getRobotStates() {
    return robotStates;
  }

  public ArrayList<Machine> getMachineStates() {
    return machineStates;
  }

  public void setSystemStateId(Integer systemStateId) {
    this.systemStateId = systemStateId;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public void setStacker(Stacker stacker) {
    this.stacker = stacker;
  }

  public void setRobotStates(ArrayList<Robot> robotStates) {
    this.robotStates = robotStates;
  }

  public void setMachineStates(ArrayList<Machine> machineStates) {
    this.machineStates = machineStates;
  }
}
