package ru.bmstu.rk9.mechanics.commands;

import ru.bmstu.rk9.mechanics.commands.messages.ConveyorMessage;

import java.util.ArrayList;

/**
 * Created by farid on 4/21/17.
 */
public class ConveyorCommand extends Command {
    public String messageType = "32b85a0115d881627ac9";
    public ArrayList<ConveyorMessage> messages = new ArrayList<>();

    public ConveyorCommand() {
        ConveyorMessage conveyor = new ConveyorMessage();
        messages.add(conveyor);
    }
}
