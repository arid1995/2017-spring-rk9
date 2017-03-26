package ru.bmstu.rk9.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by farid on 3/24/17.
 */
@RestController
public class DispatchController {
    @RequestMapping(path = "/api/dispatch/request", method = RequestMethod.GET)
    public ResponseEntity dispatch() {

        return ResponseEntity.ok().body("Отдиспетчил тип");
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ResponseEntity test() {
        return ResponseEntity.ok().body("Hello, this is Johnny knoxwill");
    }
}
