package ru.bmstu.rk9.services;

import org.springframework.stereotype.Service;
import ru.bmstu.rk9.dao.SystemStateDAO;
import ru.bmstu.rk9.models.*;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by farid on 4/21/17.
 */
@Service
public class SystemStateService {
    private SystemStateDAO currentState;
    private Conveyor conveyor;
    private Machine machine1;
    private Machine machine2;
    private Robot robot1;
    private Robot robot2;
    private Stacker stacker;
    private Stock stock;

    public SystemStateDAO getCurrentState() {
        if (currentState == null) {
            loadLastSystemState();
        }

        return currentState;
    }

    public void setCurrentState(SystemStateDAO currentState) {
        this.currentState = currentState;
        try {
            currentState.persist();
        } catch (SQLException e) {
            Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }
    }

    private void loadLastSystemState() {
        currentState = new SystemStateDAO();

        try {
            currentState.loadLast();
        } catch (SQLException e) {
            Logger.getLogger(Logger.class.getName()).log(Level.WARNING, e.getMessage(), e);
        }
    }
}
