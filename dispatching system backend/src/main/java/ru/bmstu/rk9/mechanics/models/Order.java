package ru.bmstu.rk9.mechanics.models;

/**
 * Created by farid on 5/11/17.
 */
public class Order {
  private Integer orderId;
  private Detail detail;
  private Integer amount;
  private Integer remained;

  public Order(Detail detail, Integer amount, Integer remained) {
    this.detail = detail;
    this.amount = amount;
    this.remained = remained;
  }

  public Order(Integer orderId, Detail detail, Integer amount, Integer remained) {
    this.orderId = orderId;
    this.detail = detail;
    this.amount = amount;
    this.remained = remained;
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

  public Integer getRemained() {
    return remained;
  }

  public void setDetail(Detail detail) {
    this.detail = detail;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public int takeBillets(int amount) {
    if (remained >= amount) {
      remained -= amount;
      return amount;
    }
    int tmp = remained;
    remained = 0;
    return tmp;
  }
}
