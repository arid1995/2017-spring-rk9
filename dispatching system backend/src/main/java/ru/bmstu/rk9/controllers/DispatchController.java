package ru.bmstu.rk9.controllers;

import com.mashape.unirest.http.Unirest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.rk9.database.Database;
import ru.bmstu.rk9.models.BuiltInMessage;
import ru.bmstu.rk9.models.Message;
import ru.bmstu.rk9.models.MessagePushRequest;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by farid on 3/24/17.
 */
@RestController
public class DispatchController {
    private final AtomicLong counter = new AtomicLong(0);
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

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity testForwarding() {
        counter.incrementAndGet();

        try {
            Database.update("INSERT INTO tracker (created, device) VALUES (" +
                    "'" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S")
                        .format(new Date(Instant.now().toEpochMilli())) + "'" +
                    ", 'd000-e000-v000-i000-c000-e001'" +
                    ");");
        } catch (SQLException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(500).body("Oops");
        }

        Message message = new BuiltInMessage("228", "plus");

        MessagePushRequest messagePushRequest = new MessagePushRequest("m0t0y0p0e3");
        messagePushRequest.push("d000-e000-v000-i000-c000-e001", message);

        return ResponseEntity.ok().body("Ok");
    }
}
