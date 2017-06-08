package ru.bmstu.rk9.mechanics.commands;

import java.util.ArrayList;
import ru.bmstu.rk9.mechanics.commands.messages.StackerMessage;

public class StackerCommand extends Command<StackerMessage> {
  public static final String MESSAGE_TYPE = "6b29d622a3f6da5e1193";

  public StackerCommand(StackerMessage... messages) {
    super(MESSAGE_TYPE, messages);
  }
}
