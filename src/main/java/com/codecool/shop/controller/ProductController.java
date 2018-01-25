package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res, Integer userId) {

        ProductDao productDataStore = ProductDaoDB.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoDB.getInstance();
        SupplierDao supplierDataStore = SupplierDaoDB.getInstance();
        OrderDao orderDataStore = OrderDaoDB.getInstance();

        HashMap<String, Object> params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        //params.put("numberOfItemInOrder", orderDataStore.getOrderForUser(userId).getNumberOfProducts());
        params.put("numberOfItemInOrder", 0);

        return new ModelAndView(params, "product/index");
    }

    public static String renderProductsByFilter(Request req, Response res) {
        ProductDao productDataStore = ProductDaoDB.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoDB.getInstance();
        SupplierDao supplierDataStore = SupplierDaoDB.getInstance();

        ProductCategory categoryByFilter = null;
        Supplier supplierByFilter = null;

        for(ProductCategory productCategory : productCategoryDataStore.getAll()) {
            if(productCategory.getName().equals(req.queryParams("categoryFilter"))) {
                categoryByFilter = productCategory;
            }
        }

        for(Supplier supplier : supplierDataStore.getAll()) {
            if(supplier.getName().equals(req.queryParams("supplierFilter"))) {
                supplierByFilter = supplier;
            }
        }

        List<Product> filteredProducts = new ArrayList<>();
        List<Product> products = productDataStore.getAll();

        for(Product product : products) {

            try {
                if (req.queryParams("supplierFilter").equals("All Suppliers") && req.queryParams("categoryFilter").equals("All Categories")) {
                    filteredProducts.add(product);
                }

                if (req.queryParams("supplierFilter").equals("All Suppliers") && product.getProductCategory().getId() == categoryByFilter.getId()) {
                    filteredProducts.add(product);
                }

                if (req.queryParams("categoryFilter").equals("All Categories") && product.getSupplier().getId() == supplierByFilter.getId()) {
                    filteredProducts.add(product);
                }

                if (product.getSupplier().getId() == supplierByFilter.getId() && product.getProductCategory().getId() == categoryByFilter.getId()) {
                    filteredProducts.add(product);
                }
            } catch (NullPointerException e) {
                continue;
            }

        }

        Gson gson = new Gson();

        return gson.toJson(filteredProducts);
    }
}
