package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;

import java.util.ArrayList;
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

    public Order getOrderForUser(Integer userId) {
        for (Order order : OrderDaoMem.getInstance().getAll()) {
            if (Objects.equals(order.getUserId(), userId)) {
                return order;
            }
        }
        return new Order(userId);
    }

    @Override
    public void add(Order order) {
        DATA.add(order);
    }


    @Override
    public Order find(int id)  {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    @Override
    public void remove(int id) { DATA.remove(find(id)); }

    @Override
    public List<Order> getAll() {
        return DATA;
    }
}
