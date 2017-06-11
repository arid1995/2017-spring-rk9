package ru.bmstu.rk9.mechanics.commands;

import ru.bmstu.rk9.mechanics.commands.messages.ConveyorMessage;

public class ConveyorCommand extends Command<ConveyorMessage> {
    public static final String MESSAGE_TYPE = "1636660af8dbb0b49055";

    public ConveyorCommand(ConveyorMessage... messages) {
        super(MESSAGE_TYPE, messages);
    }
}
