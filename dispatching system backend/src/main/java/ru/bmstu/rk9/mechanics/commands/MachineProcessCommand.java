package ru.bmstu.rk9.mechanics.commands;

import ru.bmstu.rk9.mechanics.commands.messages.MachineProcessMessage;

public class MachineProcessCommand extends Command<MachineProcessMessage> {

  public static final String MESSAGE_TYPE = "d4035b0ae585f9ef4357";

  public MachineProcessCommand(MachineProcessMessage... messages) {
    super(MESSAGE_TYPE, messages);
  }
}
