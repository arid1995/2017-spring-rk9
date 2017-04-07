package ru.bmstu.rk9.services;

import org.springframework.stereotype.Service;
import ru.bmstu.rk9.messages.Message;
import ru.bmstu.rk9.messages.todevice.MessagePushRequest;

import java.util.ArrayList;

/**
 * Created by farid on 4/7/17.
 */
@Service
public class MessageHandlerService {
    public void pushToDevice(String device, String messageType, ArrayList<? extends Message> messages) {
        MessagePushRequest messagePushRequest = new MessagePushRequest(messageType);
        messagePushRequest.push(device, messages);
    }
}
