package com.codecool.shop.dao;

public class DaoConnectionException extends DaoException{

    public DaoConnectionException(Exception e) {
        super(e);
    }

    public DaoConnectionException(String message) {
        super(message);
    }
}
