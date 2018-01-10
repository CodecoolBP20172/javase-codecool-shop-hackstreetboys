package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class OrderController {

    public static String renderOrder(Request req, Response res, Integer userId) {

        Integer productId = Integer.valueOf(req.queryParams("productId"));
        Product product = ProductDaoMem.getInstance().find(productId);
        Order order = OrderDaoMem.getInstance().getOrderForUser(userId);

        order.add(product);

        Gson gson = new Gson();
        return gson.toJson(order.getNumberOfProducts());
    }

    public static Map renderModal(Request req, Response res, Integer userId) {

        Order order = OrderDaoMem.getInstance().getOrderForUser(userId);

        Map params = new HashMap<>();
        params.put("shoppingOrder", order.getAll());
        params.put("totalPrice", order.getTotalPrice());

        return params;
    }
}
