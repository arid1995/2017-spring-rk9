package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by farid on 5/2/17.
 */
public class Billet {
  private ArrayList<Process> processes;

  public Billet(Detail detail) {
    this.processes = new ArrayList<>(detail.getProcesses());
  }

  public ArrayList<Process> getProcesses() {
    return processes;
  }
}
