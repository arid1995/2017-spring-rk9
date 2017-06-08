package ru.bmstu.rk9.mechanics.commands;

import ru.bmstu.rk9.mechanics.commands.messages.Message;

public class MachineCommand extends Command<Message> {
  public static final String MESSAGE_TYPE = "dd53c9c33eaedfbdd766";

  public MachineCommand(Message... messages) {
    super(MESSAGE_TYPE, messages);
  }
}
