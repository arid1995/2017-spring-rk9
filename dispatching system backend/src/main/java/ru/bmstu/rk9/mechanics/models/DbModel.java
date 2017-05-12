package ru.bmstu.rk9.mechanics.models;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by farid on 5/12/17.
 */
public abstract class DbModel {
  public abstract int incrementAndGet();
  public abstract int getModelId();
}
