package com.codecool.shop.db;

import com.codecool.shop.dao.DaoConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.AutoCloseable;

public class ConnectionHandler implements AutoCloseable {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codeshop";
    private static final String DB_USER = "nora";
    private static final String DB_PASSWORD = "***";

    Connection connection;

    public ConnectionHandler() {
    }

    public Connection getConnection() throws DaoConnectionException {
        try {
            connection = DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new DaoConnectionException(e);
        }
        return connection;
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
            }
        }

    }
}
