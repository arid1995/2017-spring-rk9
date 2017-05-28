package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by farid on 5/2/17.
 */
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
