package ru.bmstu.rk9.network.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.rk9.network.entities.ProductionTaskEntity;
import ru.bmstu.rk9.network.services.MessageHandlerService;
import ru.bmstu.rk9.network.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by farid on 3/24/17.
 */
@RestController
public class DispatchController extends Controller {
    private final MessageHandlerService messageHandlerService;
    private final TaskService taskService;
    private final AtomicLong counter = new AtomicLong(0);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public DispatchController(MessageHandlerService messageHandlerService, TaskService taskService) {
        this.messageHandlerService = messageHandlerService;
        this.taskService = taskService;
    }

    @RequestMapping(path = "/api/dispatch/request", method = RequestMethod.POST)
    public ResponseEntity dispatch() {
        return ResponseEntity.ok().body(counter);
    }

    @RequestMapping(path = "/api/dispatch/addtask", method = RequestMethod.POST)
    public ResponseEntity startTask(@RequestBody ProductionTaskEntity task) {
        String violations = getViolationString(task);
        if (!violations.equals(""))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(violations);

        taskService.addTask(task);

        return ResponseEntity.ok().body("Task accepted");
    }

    @RequestMapping(path = "/api/dispatch/shutdown", method = RequestMethod.DELETE)
    public void shutDown() {
        System.exit(0);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleException(HttpServletRequest request, HttpMessageNotReadableException ex) {
        try {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(wrapErrorMessage(WRONG_PARAMETER_TYPE));
        } catch (JsonProcessingException e) {
            Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something is wrong");
        }
    }
}
