package ru.bmstu.rk9.mechanics.commands.messages;

/**
 * Created by Александр on 13.05.2017.
 */
public class ConveyorMessage extends Message {
    public Integer palletNumber = 0;
    public Integer destinationKey = 0;
    public Long timestamp;

    public ConveyorMessage(){

    }
}

