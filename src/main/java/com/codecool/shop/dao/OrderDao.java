package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface is part of the dao, furthermore it is responsible for the webshop's orders.
 * <p>It provides access to the database or the other persistence storage (memory).</p>
 * (It depends on the implementing class.)
 * <p>Here you can add / find / remove orders and also you can access all of them.</p>
 */
public interface OrderDao {
    /**
     * Method signature of adding orders
     *
     * @param order collection of products what the user would like to buy
     */
    void add(Order order) throws DaoException;

    /**
     * Method signature of finding orders
     *
     * @param id unique identifier what belongs to one specific order what you would like to find
     * @return the order which belongs to that unique id
     */
    Order find(int id) throws DaoException;

    /**
     * Method signature of removing orders
     *
     * @param id unique identifier what belongs to one specific order what you would like to remove
     */
    void remove(int id) throws DaoException;

    /**
     * Method signature of accessing all orders.
     *
     * @return all of the orders
     */
    List<Order> getAll() throws DaoException;

}
