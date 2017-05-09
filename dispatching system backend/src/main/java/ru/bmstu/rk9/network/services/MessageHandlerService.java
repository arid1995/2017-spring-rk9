package ru.bmstu.rk9.network.services;

import org.springframework.stereotype.Service;
import ru.bmstu.rk9.messages_deprecated.Message;
import ru.bmstu.rk9.messages_deprecated.todevice.MessagePushRequest;

/**
 * Created by farid on 4/7/17.
 */
@Service
public class MessageHandlerService {
    public void pushToDevice(String device, String messageType, Message... messages) {
        MessagePushRequest messagePushRequest = new MessagePushRequest(messageType);
        messagePushRequest.push(device, messages);
    }
}
