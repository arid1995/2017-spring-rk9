package ru.bmstu.rk9.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatasourceAdapter {
    private static DataSource hanaDataSource = null;
    private static ComboPooledDataSource desktopDataSource = null;

    static {
        try {
            InitialContext ctx = new InitialContext();
            hanaDataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");
        } catch (NamingException ex) {
            hanaDataSource = null;
            desktopDataSource = new ComboPooledDataSource();
            try {
                desktopDataSource.setDriverClass("com.sap.db.jdbc.Driver");
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
            desktopDataSource.setJdbcUrl(Credentials.HOST);
            desktopDataSource.setUser(Credentials.USER);
            desktopDataSource.setPassword(Credentials.PASSWORD);
            desktopDataSource.setAcquireRetryAttempts(5);
            desktopDataSource.setAcquireIncrement(3);
            desktopDataSource.setMaxPoolSize(20);
        }
    }

    Connection getConnection() throws SQLException {
        if (desktopDataSource == null) {
            return hanaDataSource.getConnection();
        } else {
            return desktopDataSource.getConnection();
        }
    }
}
