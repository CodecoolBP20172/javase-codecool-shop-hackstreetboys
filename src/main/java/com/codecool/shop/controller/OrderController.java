package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
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

    public static ModelAndView shopCart(Request req, Response res, Integer userId) {


        Order order = OrderDaoMem.getInstance().getOrderForUser(userId);

        Map params = new HashMap<>();
        params.put("shoppingOrder", order.getAll().keySet());

        return new ModelAndView(params, "product/modal");
    }
}
