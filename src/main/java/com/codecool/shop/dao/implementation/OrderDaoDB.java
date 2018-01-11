package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.db.ConnectionHandler;
import com.codecool.shop.model.Order;
import java.sql.*;
import java.util.List;
import java.lang.AutoCloseable;

public class OrderDaoDB implements OrderDao {


    public Order getOrderForUser(Integer userId) { //TODO
        return null;
    }

    @Override
    public void add(Order order) {
    }


    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
