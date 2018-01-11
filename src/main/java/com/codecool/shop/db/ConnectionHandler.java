package com.codecool.shop.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionHandler {

    private static final String DATABASE = "jdbc:postgresql://localhost:5432/codeshop";
    private static final String DB_USER = "daniel";
    private static final String DB_PASSWORD = "asus7777";


    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DATABASE,
                DB_USER,
                DB_PASSWORD);
    }

}
