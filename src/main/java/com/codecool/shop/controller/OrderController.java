package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.OrderDaoMem;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class OrderController {

    public static ModelAndView renderOrder(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        //ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        //SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        OrderDao orderDataStore = OrderDaoMem.getInstance();

        Integer productId = Integer.valueOf(req.params(":prod.id"));
        Product productToAddCart = productDataStore.find(productId);
        orderDataStore.add(productToAddCart);
        //System.out.println(orderDataStore.getAll().get(productToAddCart));
        System.out.println(orderDataStore.getAll());

        /*Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("orderItems", orderDataStore.getAll());*/
        //return new ModelAndView(params, "product/index");
        String path = req.headers("Referer");
        res.redirect(path);
        return null;
    }

}
