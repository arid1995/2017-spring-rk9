package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by farid on 4/7/17.
 */
public interface Dao <T> {
    void persist(T object);
    //void update(T object);
    T getLast();
    //ArrayList<T> getLast(int count);
    //ArrayList<T> getAll();
}
