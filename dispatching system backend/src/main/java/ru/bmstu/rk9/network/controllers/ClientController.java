package ru.bmstu.rk9.network.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.rk9.mechanics.dao.DetailDao;
import ru.bmstu.rk9.mechanics.dao.OrderDao;
import ru.bmstu.rk9.mechanics.models.Detail;
import ru.bmstu.rk9.mechanics.models.Order;
import ru.bmstu.rk9.network.entities.OrderEntity;
import ru.bmstu.rk9.network.services.TaskService;

/**
 * Created by farid on 5/14/17.
 */
@RestController
@RequestMapping(path = "")
public class ClientController extends Controller {
  private final TaskService taskService;

  @Autowired
  public ClientController(TaskService taskService) {
    this.taskService = taskService;
  }

  @RequestMapping(path = "/detail", method = RequestMethod.POST)
  public ResponseEntity addNewOrder(@RequestBody OrderEntity body) {
    OrderDao orderDao = new OrderDao();
    DetailDao detailDao = new DetailDao();
    Detail detail = detailDao.getById(body.detailTypeId);

    if (detail == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong detail id");
    }

    Order order = new Order(detail, body.amount, body.amount);
    taskService.addTask(order);
    return ResponseEntity.ok().body("ok");
  }
}
