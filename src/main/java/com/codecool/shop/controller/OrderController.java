package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import java.util.HashMap;
import java.util.Map;

public class OrderController {

    public static Object renderOrder(Request req, Response res, Integer userId) {

        Integer productId = Integer.valueOf(req.queryParams("productId"));
        Product product = ProductDaoMem.getInstance().find(productId);
        Order order = OrderDaoMem.getInstance().getOrderForUser(userId);
        order.add(product);

        return order.getNumberOfProducts();
    }

    public static ModelAndView renderModal(Request req, Response res, Integer userId) {

        Order order = OrderDaoMem.getInstance().getOrderForUser(userId);
        float allPrice = 0;

        for(Product product : order.getAll().keySet()) {
            allPrice += product.getDefaultPrice() * order.getAll().get(product);
        }

        Map params = new HashMap<>();
        params.put("shoppingOrder", order.getAll());
        params.put("allPrice", allPrice);

        return new ModelAndView(params, "product/modal");
    }
}
