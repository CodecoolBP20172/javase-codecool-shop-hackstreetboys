package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderDaoMem implements OrderDao {


    private List<Order> DATA = new ArrayList<>();
    private static final OrderDaoMem instance = new OrderDaoMem();

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoMem.class);

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
        if (order!=null){
            DATA.add(order);
            logger.info(" {} is added to DATA (Order List)", order);
        } else {
            logger.warn("Added order is null");
            throw new NullPointerException();
        }

    }


    @Override
    public Order find(int id)  {
        ArrayList <Integer> maxMinId = new ArrayList();
        for (Order order:DATA) {
            maxMinId.add(order.getId());
        }
        if(id >= Collections.min(maxMinId) && id <= Collections.max(maxMinId)){
            logger.info("Order with Id:{} is found",id);
            return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        }
        else{
            logger.warn("There is no order with id:{}",id);
            throw new IllegalArgumentException();
        }
    }


    @Override
    public void remove(int id) {
        DATA.remove(find(id));
        logger.info("Order with Id:{} is removed",id);
    }


    @Override
    public List<Order> getAll() {
        logger.info("DATA (Order list) returned successfully");
        return DATA;

    }
}
