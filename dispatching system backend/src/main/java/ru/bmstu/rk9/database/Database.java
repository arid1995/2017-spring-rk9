package ru.bmstu.rk9.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("Duplicates")
public class Database {
    private static DatasourceAdapter dataSource = new DatasourceAdapter();

    public static <T> T select(String query, TResultCallback<T> callback) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeQuery(query);
                try (ResultSet result = statement.getResultSet()) {
                    return callback.call(result);
                }
            }
        }
    }

    public static void select(String query, ResultCallback callback) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeQuery(query);
                try (ResultSet result = statement.getResultSet()) {
                    callback.call(result);
                }
            }
        }
    }

    //update allows UPDATE, DELETE and INSERT queries
    public static void update(String query) throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(query);
            }
        }
    }
}
