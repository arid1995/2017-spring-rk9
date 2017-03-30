package ru.bmstu.rk9.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.rk9.database.Database;

import java.sql.SQLException;

/**
 * Created by farid on 3/24/17.
 */
@RestController
public class DispatchController {
    @RequestMapping(path = "/api/dispatch/request", method = RequestMethod.GET)
    public ResponseEntity dispatch() {
        return ResponseEntity.ok().body("Hello, this is Johnny knoxwill");
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity test() {
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
}
