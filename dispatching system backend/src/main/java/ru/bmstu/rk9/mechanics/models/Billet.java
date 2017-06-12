package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;
import java.util.LinkedList;

public class Billet {
  private LinkedList<Process> processes;
  private Integer palletId;
  private Integer palletPosition;

  public Billet(Detail detail, Integer palletId) {
    this.processes = new LinkedList<>(detail.getProcesses());
    this.palletId = palletId;
  }

  public LinkedList<Process> getProcesses() {
    return processes;
  }

  public Integer getPalletId() {
    return palletId;
  }

  public Process popNextProcess() {
    return processes.poll();
  }

  public Process getNextProcess() {
    return processes.peek();
  }

  public Integer getPalletPosition() {
    return palletPosition;
  }

  public void setPalletPosition(Integer palletPosition) {
    this.palletPosition = palletPosition;
  }
}
