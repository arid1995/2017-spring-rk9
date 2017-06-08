package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;

public class Billet {
  private ArrayList<Process> processes;
  private Integer palletId;

  public Billet(Detail detail, Integer palletId) {
    this.processes = new ArrayList<>(detail.getProcesses());
    this.palletId = palletId;
  }

  public ArrayList<Process> getProcesses() {
    return processes;
  }

  public Integer getPalletId() {
    return palletId;
  }
}
