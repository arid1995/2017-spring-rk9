package ru.bmstu.rk9.network.entities;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by farid on 5/2/17.
 */
public class ProductionTaskEntity implements Comparable<ProductionTaskEntity> {
    @NotNull
    @Min(1)
    private Integer amount;

    @NotNull
    private Integer detailTypeId;
    private Integer priority;

    public Integer getAmount() {
        return amount;
    }

    public Integer getDetailTypeId() {
        return detailTypeId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setDetailTypeId(Integer detailTypeId) {
        this.detailTypeId = detailTypeId;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public int compareTo(ProductionTaskEntity task) {
        if (priority == null || task.priority == null) {
            return 0;
        } else {
            return priority - task.priority;
        }
    }
}
