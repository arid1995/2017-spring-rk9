package ru.bmstu.rk9.network.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;
import ru.bmstu.rk9.network.entities.FeedbackMessageEntity;
import ru.bmstu.rk9.network.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by farid on 3/24/17.
 */
@RestController
@RequestMapping("/api/dispatch")
public class DispatchController extends Controller {

  private final TaskService taskService;
  private final AtomicLong counter = new AtomicLong(0);
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  public DispatchController(TaskService taskService) {
    this.taskService = taskService;
  }

  @RequestMapping(path = "/machine", method = RequestMethod.POST)
  public ResponseEntity handleMachineMessage(@RequestBody FeedbackMessageEntity body) {
    System.out.print("I think I just came!");
    taskService.handleMessage(body);
    return ResponseEntity.ok("ok");
  }

  @RequestMapping(path = "/robot", method = RequestMethod.POST)
  public ResponseEntity handleRobotMessage(@RequestBody FeedbackMessageEntity body) {
    taskService.handleMessage(body);
    return ResponseEntity.ok("ok");
  }

  @RequestMapping(path = "/conveyor", method = RequestMethod.POST)
  public ResponseEntity handleConveyorMessage(@RequestBody FeedbackMessageEntity body) {
    taskService.handleMessage(body);
    return ResponseEntity.ok("ok");
  }

  @RequestMapping(path = "/stacker", method = RequestMethod.POST)
  public ResponseEntity handleStackerMessage(@RequestBody FeedbackMessageEntity body) {
    taskService.handleMessage(body);
    return ResponseEntity.ok("ok");
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity handleException(HttpServletRequest request,
      HttpMessageNotReadableException ex) {
    try {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body(wrapErrorMessage(WRONG_PARAMETER_TYPE));
    } catch (JsonProcessingException e) {
      Logger.getLogger(Logger.class.getName()).log(Level.SEVERE, e.getMessage(), e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something is wrong");
    }
  }
}
