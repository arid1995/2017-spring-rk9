package ru.bmstu.rk9.mechanics.commands;

import ru.bmstu.rk9.mechanics.commands.messages.ConveyorMessage;

public class ConveyorCommand extends Command<ConveyorMessage> {
    public static final String MESSAGE_TYPE = "32b85a0115d881627ac9";

    public ConveyorCommand(ConveyorMessage... messages) {
        super(MESSAGE_TYPE, messages);
    }
}
