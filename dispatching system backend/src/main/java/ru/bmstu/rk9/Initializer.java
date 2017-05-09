package ru.bmstu.rk9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.bmstu.rk9.database.TableManager;
import ru.bmstu.rk9.network.services.SystemStateService;

/**
 * Created by farid on 4/21/17.
 */
@Component
class Initializer {
    @Autowired
    SystemStateService systemStateService;

    void initialize() {
        TableManager tableManager = new TableManager();
        tableManager.createTables();
        loadCurrentState();
    }

    private void loadCurrentState() {

    }
}
