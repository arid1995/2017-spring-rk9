package ru.bmstu.rk9.network.controllers;

import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.bmstu.rk9.mechanics.dao.DetailDao;
import ru.bmstu.rk9.mechanics.dao.MachineDao;
import ru.bmstu.rk9.mechanics.dao.ProcessDao;
import ru.bmstu.rk9.mechanics.dao.RobotDao;
import ru.bmstu.rk9.mechanics.models.Detail;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Process;
import ru.bmstu.rk9.mechanics.models.Robot;
import ru.bmstu.rk9.network.entities.DetailEntity;
import ru.bmstu.rk9.network.entities.MachineEntity;
import ru.bmstu.rk9.network.entities.ProcessEntity;
import ru.bmstu.rk9.network.entities.ProcessToMachineEntity;
import ru.bmstu.rk9.network.entities.RobotEntity;

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

  @RequestMapping(path = "/process", method = RequestMethod.POST)
  public ResponseEntity addProcess(@RequestBody ProcessEntity body) {
    ProcessDao processDao = new ProcessDao();
    Process process = new Process(body.programName, body.duration);
    processDao.persist(process);

    return ResponseEntity.ok().body("ok");
  }

  @RequestMapping(path = "/process-to-machine", method = RequestMethod.POST)
  public ResponseEntity linkProcessAndMachine(@RequestBody ProcessToMachineEntity body) {
    MachineDao machineDao = new MachineDao();
    ProcessDao processDao = new ProcessDao();
    Machine machine = machineDao.getById(body.machineId);
    Process process = processDao.getById(body.processId);

    if (machine == null || process == null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong ids");
    }
    machineDao.addProcessToMachine(process, machine);
    return ResponseEntity.ok().body("ok");
  }

  @RequestMapping(path = "/detail", method = RequestMethod.POST)
  public ResponseEntity addDetail(@RequestBody DetailEntity body) {
    ProcessDao processDao = new ProcessDao();
    ArrayList<Process> processes = new ArrayList<>();

    for (Integer processId : body.processIds) {
      Process process = processDao.getById(processId);
      if (process == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Process does not exist");
      }
      processes.add(process);
    }

    Detail detail = new Detail(body.name, processes);
    DetailDao detailDao = new DetailDao();
    detailDao.persist(detail);

    return ResponseEntity.ok().body("ok");
  }
}
