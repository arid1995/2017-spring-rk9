package ru.bmstu.rk9.mechanics.commands;

import ru.bmstu.rk9.mechanics.commands.messages.ConveyorMessage;

public class ConveyorCommand extends Command<ConveyorMessage> {
    public String messageType = "32b85a0115d881627ac9";

    public ConveyorCommand() {
        ConveyorMessage conveyor = new ConveyorMessage();
        messages.add(conveyor);
    }
}
