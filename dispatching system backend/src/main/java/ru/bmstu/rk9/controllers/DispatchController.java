package ru.bmstu.rk9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.rk9.Devices;
import ru.bmstu.rk9.messages.fromdevice.DBuiltInMessage;
import ru.bmstu.rk9.messages.fromdevice.IncomingRequest;
import ru.bmstu.rk9.messages.todevice.SBuiltInMessage;
import ru.bmstu.rk9.messages.todevice.SMessageTypes;
import ru.bmstu.rk9.services.MessageHandlerService;
import ru.bmstu.rk9.dao.TrackerDAO;

import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by farid on 3/24/17.
 */
@RestController
public class DispatchController {
    private final AtomicLong counter = new AtomicLong(0);

    private final MessageHandlerService messageHandlerService;

    @Autowired
    public DispatchController(MessageHandlerService messageHandlerService) {
        this.messageHandlerService = messageHandlerService;
    }

    @RequestMapping(path = "/api/dispatch/request", method = RequestMethod.POST)
    public ResponseEntity dispatch() {
        return ResponseEntity.ok().body(counter);
    }

    @RequestMapping(path = "/api/dispatch/default", method = RequestMethod.POST)
    public ResponseEntity handleDefaultMessage(@RequestBody IncomingRequest<DBuiltInMessage> requestBody) {
        counter.incrementAndGet();

        try {
            TrackerDAO trackerDAO = new TrackerDAO(Devices.getDeviceId("default"), "ok");
            trackerDAO.persist();
        } catch (SQLException ex) {
            Logger log = Logger.getLogger(Logger.class.getName());
            log.log(Level.WARNING, ex.getMessage());
            return ResponseEntity.status(500).body("Oops");
        }

        SBuiltInMessage message = new SBuiltInMessage(Long.toString((new Random()).nextLong()),
                Float.toString((new Random()).nextFloat()));

        messageHandlerService.pushToDevice(Devices.getDeviceId("default"), SMessageTypes.BUILT_IN_PUSH_TYPE,
                message);

        return ResponseEntity.ok().body("Ok");
    }
}
