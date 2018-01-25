package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
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
        System.out.println(productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        System.out.println(supplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        System.out.println(productDataStore.getAll());
        params.put("numberOfItemInOrder", orderDataStore.find(userId).getNumberOfProducts());

        return new ModelAndView(params, "product/index");
    }

    public static String renderProductsByFilter(Request req, Response res) {
        ProductDao productDataStore = ProductDaoDB.getInstance();
        List<Product> products = productDataStore.getAll();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoDB.getInstance();
        SupplierDao supplierDataStore = SupplierDaoDB.getInstance();

        Supplier supplierByFilter = supplierDataStore.getAll().stream().filter(supplier ->
                Objects.equals(supplier.getName(), req.queryParams("supplierFilter"))).findFirst().orElse(null);
        ProductCategory categoryByFilter = productCategoryDataStore.getAll().stream().filter(category ->
                Objects.equals(category.getName(), req.queryParams("categoryFilter"))).findFirst().orElse(null);

        System.out.println(supplierByFilter);
        System.out.println(categoryByFilter);


        List<Product> filteredProducts = productDataStore.getAll().stream().filter(product ->
                (product.getSupplier().getId() == supplierByFilter.getId() || Objects.equals(req.queryParams("supplierFilter"), "All Suppliers"))
                        && (product.getProductCategory().getId() == categoryByFilter.getId() || Objects.equals(req.queryParams("categoryFilter"), "All Categories"))).collect(Collectors.toList());


        System.out.println(filteredProducts);
        Gson gson = new Gson();
        return gson.toJson(filteredProducts);
    }
}
