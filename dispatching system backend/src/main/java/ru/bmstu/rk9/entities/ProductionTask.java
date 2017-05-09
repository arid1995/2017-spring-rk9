package ru.bmstu.rk9.entities;

import java.util.ArrayList;

/**
 * Created by farid on 5/2/17.
 */
public class ProductionTask implements Comparable<ProductionTask> {
    private Integer detailCount;
    private Integer processId;
    private ArrayList<Integer> machineSequence;
    private Integer priority;

    public Integer getDetailCount() {
        return detailCount;
    }

    public Integer getProcessId() {
        return processId;
    }

    public ArrayList<Integer> getMachineSequence() {
        return machineSequence;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setDetailCount(Integer detailCount) {
        this.detailCount = detailCount;
    }

    public void setProcessId(Integer processId) {
        this.processId = processId;
    }

    public void setMachineSequence(ArrayList<Integer> machineSequence) {
        this.machineSequence = machineSequence;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public int compareTo(ProductionTask task) {
        if (priority == null || task.priority == null) {
            return 0;
        } else {
            return priority - task.priority;
        }
    }
}
