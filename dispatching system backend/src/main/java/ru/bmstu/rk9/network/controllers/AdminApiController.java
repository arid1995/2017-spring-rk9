package ru.bmstu.rk9.network.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.rk9.mechanics.models.Machine;

/**
 * Created by farid on 5/11/17.
 */
@RestController
@RequestMapping("api/admin")
public class AdminApiController {
    @RequestMapping(path = "/machine", method = RequestMethod.POST)
    public ResponseEntity addMachine(@RequestBody Machine machine) {
        return ResponseEntity.ok().body("");
    }
}
