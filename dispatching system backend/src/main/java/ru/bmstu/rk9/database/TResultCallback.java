package ru.bmstu.rk9.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by farid on 3/26/17.
 */
public interface TResultCallback<T> {
    T call(ResultSet result) throws SQLException;
}
