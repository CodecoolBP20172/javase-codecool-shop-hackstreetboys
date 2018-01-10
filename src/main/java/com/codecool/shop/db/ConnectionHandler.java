package com.codecool.shop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionHandler {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/farkasdaniel";
    private static final String DB_USER = "farkasdaniel";
    private static final String DB_PASSWORD = "1211";


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

}
