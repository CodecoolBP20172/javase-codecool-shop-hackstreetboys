package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.sql.SQLException;
import java.util.*;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res, Integer userId) throws SQLException {

        ProductDao productDataStore = ProductDaoDB.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoDB.getInstance();
        SupplierDao supplierDataStore = SupplierDaoDB.getInstance();
        OrderDao orderDataStore = OrderDaoDB.getInstance();

        HashMap<String, Object> params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        params.put("numberOfItemInOrder", orderDataStore.find(userId).getNumberOfProducts());

        return new ModelAndView(params, "product/index");
    }

    public static String renderProductsByFilter(Request req, Response res) {
        ProductDao productDataStore = ProductDaoDB.getInstance();
        List<Product> products = new ArrayList<>(productDataStore.getAll());

        for (Product product : productDataStore.getAll()) {

            if ((!Objects.equals(product.getSupplier().getName(), req.queryParams("supplierFilter")))
                    && (!Objects.equals(req.queryParams("supplierFilter"), "All Suppliers"))) {
                products.remove(product);
                continue;
            }

            if ((!Objects.equals(product.getProductCategory().getName(), req.queryParams("categoryFilter")))
                    && (!Objects.equals(req.queryParams("categoryFilter"), "All Categories"))) {
                products.remove(product);
            }
        }
        Gson gson = new Gson();
        return gson.toJson(products);
    }
}
