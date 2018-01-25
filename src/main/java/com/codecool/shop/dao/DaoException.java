package com.codecool.shop.dao;

import java.sql.SQLException;

public class DaoException extends Exception {

    public DaoException(Exception e) {
        super(e);
    }

    public DaoException(String message) {
        super(message);
    }
}
