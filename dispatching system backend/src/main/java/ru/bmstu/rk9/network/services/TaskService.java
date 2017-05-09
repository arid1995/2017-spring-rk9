package ru.bmstu.rk9.network.services;

import org.springframework.stereotype.Service;
import ru.bmstu.rk9.mechanics.schedule.Dispatcher;
import ru.bmstu.rk9.network.entities.ProductionTask;

import java.util.PriorityQueue;

/**
 * Created by farid on 5/2/17.
 */
@Service
public class TaskService {
    private Dispatcher dispatcher = new Dispatcher(2);

    public void addTask(ProductionTask task) {
        dispatcher.addTask(task);
    }

    public PriorityQueue<ProductionTask> getTaskQueue() {
        return dispatcher.getTasks();
    }
}
