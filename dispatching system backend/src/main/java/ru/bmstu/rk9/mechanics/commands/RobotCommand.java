package ru.bmstu.rk9.mechanics.commands;

import ru.bmstu.rk9.mechanics.commands.messages.RobotMessage;

public class RobotCommand extends Command<RobotMessage> {

  public static final String MESSAGE_TYPE = "322f9ecd48e90f0d3f55";

  public RobotCommand(RobotMessage... messages) {
    super(MESSAGE_TYPE, messages);
  }
}
