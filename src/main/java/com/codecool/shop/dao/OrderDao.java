package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {

    void add(Order order);
    Order find(int id);
    void remove(int id);

    //HashMap<Product, Integer> getAll();
    List<Order> getAll();
    /*List<Product> getBy(Supplier supplier);
    List<Product> getBy(ProductCategory productCategory);*/
}
