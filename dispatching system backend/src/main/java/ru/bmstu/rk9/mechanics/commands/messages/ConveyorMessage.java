package ru.bmstu.rk9.mechanics.commands.messages;

public class ConveyorMessage extends Message {
    public Integer palletNumber = 0;
    public Integer destinationKey = 0;
    public Long timestamp;

    public ConveyorMessage(){

    }
}

