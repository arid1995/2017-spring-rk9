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
@RequestMapping("/api/dispatch")
public class DispatchController extends Controller {

  private final MessageHandlerService messageHandlerService;
  private final AtomicLong counter = new AtomicLong(0);
  private ObjectMapper mapper = new ObjectMapper();

  @Autowired
  public DispatchController(MessageHandlerService messageHandlerService, TaskService taskService) {
    this.messageHandlerService = messageHandlerService;
  }

  @RequestMapping(path = "/request", method = RequestMethod.POST)
  public ResponseEntity dispatch() {
    return ResponseEntity.ok().body(counter);
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
