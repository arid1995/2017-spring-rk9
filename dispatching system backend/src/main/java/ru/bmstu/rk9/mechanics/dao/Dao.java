package ru.bmstu.rk9.mechanics.dao;

import java.sql.SQLException;

/**
 * Created by farid on 4/7/17.
 */
public interface Dao {
    void persist() throws SQLException;
    void loadLast() throws SQLException;
}
