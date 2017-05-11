package ru.bmstu.rk9.mechanics.models;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * Created by farid on 5/11/17.
 */
public class SystemState {
  private Integer stateId;
  private Timestamp created;
  private Stacker stacker;
  private ArrayList<Robot> robotState;
  private ArrayList<Machine> machineState;

  public SystemState(Timestamp created, Stacker stacker,
      ArrayList<Robot> robotState,
      ArrayList<Machine> machineState) {
    this.created = created;
    this.stacker = stacker;
    this.robotState = robotState;
    this.machineState = machineState;
  }

  public Integer getStateId() {
    return stateId;
  }

  public Timestamp getCreated() {
    return created;
  }

  public Stacker getStacker() {
    return stacker;
  }

  public ArrayList<Robot> getRobotState() {
    return robotState;
  }

  public ArrayList<Machine> getMachineState() {
    return machineState;
  }

  public void setCreated(Timestamp created) {
    this.created = created;
  }

  public void setStacker(Stacker stacker) {
    this.stacker = stacker;
  }

  public void setRobotState(ArrayList<Robot> robotState) {
    this.robotState = robotState;
  }

  public void setMachineState(ArrayList<Machine> machineState) {
    this.machineState = machineState;
  }
}
