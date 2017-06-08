package ru.bmstu.rk9.mechanics.dao;

import java.util.ArrayList;

public interface Dao <T> {
    int persist(T object);
    //void update(T object);
    T getLast();
    //ArrayList<T> getLast(int count);
    ArrayList<T> getAll();
    default void delete(T object) {}
}
