package ru.bmstu.rk9.mechanics.models;

import java.util.ArrayList;

/**
 * Created by farid on 5/11/17.
 */
public class Detail {

  private Integer detailId;
  private String name;
  ArrayList<Process> processes;

  public Detail(Integer detailId, String name,
      ArrayList<Process> processes) {
    this.detailId = detailId;
    this.name = name;
    this.processes = processes;
  }

  public Integer getDetailId() {
    return detailId;
  }

  public String getName() {
    return name;
  }

  public void setDetailId(Integer detailId) {
    this.detailId = detailId;
  }

  public void setName(String name) {
    this.name = name;
  }
}
