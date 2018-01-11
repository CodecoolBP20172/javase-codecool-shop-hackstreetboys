package com.codecool.shop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.lang.AutoCloseable;

public class ConnectionHandler implements AutoCloseable {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/farkasdaniel";
    private static final String DB_USER = "farkasdaniel";
    private static final String DB_PASSWORD = "1211";

    Connection connection;

    public ConnectionHandler()  {
    }

    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(DATABASE, DB_USER, DB_PASSWORD);
        return connection;
    }

    @Override
    public void close()  {
        if (connection != null) {
           try {
               connection.close();
           } catch (Exception e) {
           }
        }

    }
}
