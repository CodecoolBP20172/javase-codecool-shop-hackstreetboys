package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DaoException;
import com.codecool.shop.dao.DaoRecordNotFoundException;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OrderDaoMem implements OrderDao {


    private List<Order> DATA = new ArrayList<>();
    private static final OrderDaoMem instance = new OrderDaoMem();

    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        return instance;
    }

    public Order getOrderForUser(Integer userId) throws DaoException {
        for (Order order : OrderDaoMem.getInstance().getAll()) {
            if (Objects.equals(order.getUserId(), userId)) {
                return order;
            }
        }
        return new Order(userId);
    }


    @Override
    public void add(Order order) throws DaoException {
        if (order != null) {
            DATA.add(order);
        } else {
            //throw new NullPointerException();
            throw new DaoException("tried to add null");
        }
    }


    @Override
    public Order find(int id) throws DaoException {
        ArrayList<Integer> maxMinId = new ArrayList();
        for (Order order : DATA) {
            maxMinId.add(order.getId());
        }
        if (id >= Collections.min(maxMinId) && id <= Collections.max(maxMinId)) {
            return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        } else {
            throw new DaoRecordNotFoundException("couldn't find order with id " + id);
        }
    }


    @Override
    public void remove(int id) throws DaoException {
        DATA.remove(find(id));
    }


    @Override
    public List<Order> getAll() {
        return DATA;
    }
}
