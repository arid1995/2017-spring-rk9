package ru.bmstu.rk9.network.services;

import org.springframework.stereotype.Service;
import ru.bmstu.rk9.mechanics.schedule.Dispatcher;
import ru.bmstu.rk9.network.entities.ProductionTaskEntity;

import java.util.PriorityQueue;

/**
 * Created by farid on 5/2/17.
 */
@Service
public class TaskService {
    private Dispatcher dispatcher = new Dispatcher(2);

    public void addTask(ProductionTaskEntity task) {
        dispatcher.addTask(task);
    }

    public PriorityQueue<ProductionTaskEntity> getTaskQueue() {
        return dispatcher.getTasks();
    }
}
