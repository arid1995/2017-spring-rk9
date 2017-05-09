package ru.bmstu.rk9.network.services;

import org.springframework.stereotype.Service;
import ru.bmstu.rk9.network.entities.ProductionTask;

import java.util.PriorityQueue;

/**
 * Created by farid on 5/2/17.
 */
@Service
public class TaskService {
    private PriorityQueue<ProductionTask> tasks = new PriorityQueue<>();

    public void addTask(ProductionTask task) {
        tasks.add(task);
    }

    public ProductionTask pollTask() {
        return tasks.poll();
    }
}
