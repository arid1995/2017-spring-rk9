package ru.bmstu.rk9.mechanics.schedule;

import ru.bmstu.rk9.mechanics.models.Conveyor;
import ru.bmstu.rk9.mechanics.models.Machine;
import ru.bmstu.rk9.mechanics.models.Robot;
import ru.bmstu.rk9.mechanics.models.Stacker;
import ru.bmstu.rk9.network.entities.ProductionTask;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by farid on 4/29/17.
 */
public class Dispatcher {
    private final PriorityQueue<ProductionTask> tasks = new PriorityQueue<>();
    private boolean isWorking = false;
    private final ArrayList<Machine> machines;
    private final ArrayList<Robot> robots;
    private final Conveyor conveyor;
    private final Stacker stacker;

    public Dispatcher(int machineNumber) {
        machines = new ArrayList<>();
        robots = new ArrayList<>();
        for (int i = 0; i < machineNumber; i++) {
            machines.add(new Machine());
            robots.add(new Robot());
        }
        conveyor = new Conveyor();
        stacker = new Stacker();
    }

    public void addTask(ProductionTask task) {
        tasks.add(task);

        if (isWorking)
            return;

        isWorking = true;
        start();
    }

    public PriorityQueue<ProductionTask> getTasks() {
        return new PriorityQueue<>(tasks);
    }

    public void start() {

    }
}