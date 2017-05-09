package ru.bmstu.rk9.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.rk9.Devices;
import ru.bmstu.rk9.entities.ProductionTask;
import ru.bmstu.rk9.messages_deprecated.fromdevice.DBuiltInMessage;
import ru.bmstu.rk9.messages_deprecated.fromdevice.IncomingRequest;
import ru.bmstu.rk9.messages_deprecated.todevice.SBuiltInMessage;
import ru.bmstu.rk9.messages_deprecated.todevice.SMessageTypes;
import ru.bmstu.rk9.services.MessageHandlerService;
import ru.bmstu.rk9.dao.TrackerDAO;
import ru.bmstu.rk9.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.*;
import java.sql.SQLException;
import java.util.Random;
import java.util.Set;
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
    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Autowired
    public DispatchController(MessageHandlerService messageHandlerService, TaskService taskService) {
        this.messageHandlerService = messageHandlerService;
        this.taskService = taskService;
    }

    private <T> String getViolationString(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        StringBuilder violationString = new StringBuilder();

        for (ConstraintViolation<T> violation : violations) {
            violationString.append(violation.getPropertyPath()).append(" ")
                    .append(violation.getMessage()).append("\n");
        }
        return violationString.toString();
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
            log.log(Level.WARNING, ex.getMessage(), ex);
            return ResponseEntity.status(500).body("Oops");
        }

        SBuiltInMessage message = new SBuiltInMessage(Long.toString((new Random()).nextLong()),
                Float.toString((new Random()).nextFloat()));

        messageHandlerService.pushToDevice(Devices.getDeviceId("default"), SMessageTypes.BUILT_IN_PUSH_TYPE,
                message);

        return ResponseEntity.ok().body("Ok");
    }

    @RequestMapping(path = "/api/dispatch/addtask", method = RequestMethod.POST)
    public ResponseEntity startTask(@RequestBody ProductionTask task) {
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
