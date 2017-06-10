package ru.bmstu.rk9.mechanics.commands;

import ru.bmstu.rk9.mechanics.commands.messages.MachineMessage;

public class MachineCommand extends Command<MachineMessage> {
  public static final String MESSAGE_TYPE = "dd53c9c33eaedfbdd766";

  public MachineCommand(MachineMessage... messages) {
    super(MESSAGE_TYPE, messages);
  }
}
