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

    public static ModelAndView renderOrder(Request req, Response res, Integer userId) {

        Integer productId = Integer.valueOf(req.params(":prod.id"));
        Product product = ProductDaoMem.getInstance().find(productId);
        Order order = OrderDaoMem.getInstance().getOrderForUser(userId);
        order.add(product);

        Integer products = order.getNumberOfProducts();

        String path = req.headers("Referer");
        res.redirect(path);
        return null;
    }

}
