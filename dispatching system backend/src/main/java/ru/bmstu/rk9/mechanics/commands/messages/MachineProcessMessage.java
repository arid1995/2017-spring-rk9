package ru.bmstu.rk9.mechanics.commands.messages;

public class MachineProcessMessage extends Message {
  private String programName = "";

  public void setProgramName(String programName) {
    this.programName = programName;
  }

  public String getProgramName() {
    return programName;
  }
}
