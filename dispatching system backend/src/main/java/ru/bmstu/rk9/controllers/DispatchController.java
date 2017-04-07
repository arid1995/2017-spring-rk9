package ru.bmstu.rk9.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.rk9.Devices;
import ru.bmstu.rk9.messages.fromdevice.BuiltInMessageRequest;
import ru.bmstu.rk9.services.MessageHandlerService;
import ru.bmstu.rk9.dao.TrackerDAO;
import ru.bmstu.rk9.database.Database;

import java.sql.SQLException;
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

    @RequestMapping(path = "/api/dispatch/request", method = RequestMethod.GET)
    public ResponseEntity dispatch() {
        return ResponseEntity.ok().body(counter);
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity testDb() {
        try {
            String test = Database.select("SELECT * FROM T_IOT_M0T0Y0P0E1", (result) -> {
                StringBuilder builder = new StringBuilder();

                while (result.next()) {
                    builder.append(result.getString("G_DEVICE")).append(" ");
                    builder.append(result.getString("G_CREATED")).append(" ");
                    builder.append(result.getString("C_SENSOR")).append(" ");
                    builder.append(result.getString("C_VALUE")).append(" ");
                    builder.append(result.getString("C_TIMESTAMP")).append(" ");
                    builder.append("\n<p>");
                }

                return builder.toString();
            });

            return ResponseEntity.ok().body(test);
        } catch (SQLException ex) {
            return ResponseEntity.ok().body(ex.getMessage());
        }
    }

    @RequestMapping(path = "/api/dispatch/default", method = RequestMethod.POST)
    public ResponseEntity handleDefaultMessage(@RequestBody BuiltInMessageRequest requestBody) {
        counter.incrementAndGet();

        try {
            TrackerDAO trackerDAO = new TrackerDAO(Devices.getDeviceId("default"), "ok");
            trackerDAO.persist();
        } catch (SQLException ex) {
            Logger log = Logger.getLogger(Logger.class.getName());
            log.log(Level.WARNING, ex.getMessage());
            return ResponseEntity.status(500).body("Oops");
        }

        messageHandlerService.pushToDevice(Devices.getDeviceId("default"), requestBody.getMessageType(),
                requestBody.getMessages());

        return ResponseEntity.ok().body("Ok");
    }
}
