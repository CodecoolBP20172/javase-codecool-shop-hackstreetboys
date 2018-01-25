package com.codecool.shop.dao;

public class DaoRecordNotFoundException extends DaoException {
    public DaoRecordNotFoundException(Exception e) {
        super(e);
    }

    public DaoRecordNotFoundException(String message) {
        super(message);
    }
}
