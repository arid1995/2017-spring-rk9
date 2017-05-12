package ru.bmstu.rk9.mechanics.models;

/**
 * Created by farid on 5/11/17.
 */
public class Order extends DbModel {
  private Integer orderId;
  private Detail detail;
  private Integer amount;

  public Order(Detail detail, Integer amount) {
    this.detail = detail;
    this.amount = amount;
  }

  public Integer getOrderId() {
    return orderId;
  }

  public Detail getDetail() {
    return detail;
  }

  public Integer getAmount() {
    return amount;
  }

  public void setDetail(Detail detail) {
    this.detail = detail;
  }

  public void setAmount(Integer amount) {
    this.amount = amount;
  }

  @Override
  public int incrementAndGet() {
    return 0;
  }

  @Override
  public int getModelId() {
    return 0;
  }
}
