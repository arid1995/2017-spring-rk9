package ru.bmstu.rk9.network.services;

import org.springframework.stereotype.Service;
import ru.bmstu.rk9.mechanics.dao.SystemStateDao;
import ru.bmstu.rk9.mechanics.models.devices.Conveyor;
import ru.bmstu.rk9.mechanics.models.devices.Machine;
import ru.bmstu.rk9.mechanics.models.devices.Robot;
import ru.bmstu.rk9.mechanics.models.devices.Stacker;
import ru.bmstu.rk9.mechanics.models.Stock;

@Service
public class SystemStateService {

  private SystemStateDao currentState;
  private Conveyor conveyor;
  private Machine machine1;
  private Machine machine2;
  private Robot robot1;
  private Robot robot2;
  private Stacker stacker;
  private Stock stock;

  public SystemStateDao getCurrentState() {
    if (currentState == null) {
      loadLastSystemState();
    }

    return currentState;
  }

  public void setCurrentState(SystemStateDao currentState) {
    this.currentState = currentState;
    //currentState.persist();
  }

  private void loadLastSystemState() {
    currentState = new SystemStateDao();
    currentState.getLast();
  }
}
