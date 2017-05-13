package ru.bmstu.rk9.network.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.rk9.mechanics.dao.MachineDao;
import ru.bmstu.rk9.mechanics.dao.RobotDao;
import ru.bmstu.rk9.mechanics.models.Device;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Robot;
import ru.bmstu.rk9.network.entities.DeviceEntity;
import ru.bmstu.rk9.network.entities.MachineEntity;
import ru.bmstu.rk9.network.entities.RobotEntity;

/**
 * Created by farid on 5/11/17.
 */
@RestController
@RequestMapping("api/admin")
public class AdminApiController extends Controller {

  @RequestMapping(path = "/machine", method = RequestMethod.POST)
  public ResponseEntity addMachine(@RequestBody MachineEntity body) {
    //TODO: add validation
    MachineDao machineDao = new MachineDao();
    Machine machine = new Machine(body.deviceStringId, body.deviceName, body.machineType);
    machineDao.persist(machine);

    return ResponseEntity.ok().body("ok");
  }

  @RequestMapping(path = "/robot", method = RequestMethod.POST)
  public ResponseEntity addRobot(@RequestBody RobotEntity body) {
    //TODO: add validation
    RobotDao robotDao = new RobotDao();
    Robot robot = new Robot(body.deviceStringId, body.deviceName);
    robotDao.persist(robot);

    return ResponseEntity.ok().body("ok");
  }
}
