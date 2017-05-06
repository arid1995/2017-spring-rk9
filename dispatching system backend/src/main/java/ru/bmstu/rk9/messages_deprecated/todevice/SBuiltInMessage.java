package ru.bmstu.rk9.messages_deprecated.todevice;

import ru.bmstu.rk9.messages_deprecated.Message;

/**
 * Created by farid on 4/7/17.
 */
public class SBuiltInMessage extends Message {
    String opcode;
    String operand;

    public SBuiltInMessage() {
    }

    public SBuiltInMessage(String opcode, String operand) {
        this.opcode = opcode;
        this.operand = operand;
    }

    public String getOpcode() {
        return opcode;
    }

    public String getOperand() {
        return operand;
    }
}
