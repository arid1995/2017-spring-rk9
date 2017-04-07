package ru.bmstu.rk9.models;

/**
 * Created by farid on 4/7/17.
 */

@SuppressWarnings("FieldCanBeLocal")
public class BuiltInMessage extends Message {
    private String opcode;
    private String operand;

    public BuiltInMessage(String opcode, String operand) {
        this.opcode = opcode;
        this.operand = operand;
    }
}
