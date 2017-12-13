package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import freemarker.ext.beans.HashAdapter;
import spark.Request;
import spark.Response;
import spark.ModelAndView;

import java.util.*;

public class ProductController {

    public static ModelAndView renderProducts(Request req, Response res) {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", productDataStore.getAll());
        return new ModelAndView(params, "product/index");
    }

    public static ModelAndView renderProductsByFilter(Request req, Response res) {

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();

        List<Product> products = new ArrayList<>(productDataStore.getAll());

        for (Product product : productDataStore.getAll()) {
            if ( (!Objects.equals(product.getSupplier().getName(), req.queryParams("supplierFilter")))
                    && (!Objects.equals(req.queryParams("supplierFilter"), "All Suppliers")) ) {
                products.remove(product);
                continue;
            }

            if ( (!Objects.equals(product.getProductCategory().getName(), req.queryParams("categoryFilter")))
                    && (!Objects.equals(req.queryParams("categoryFilter"), "All Categories")) ) {
                products.remove(product);
            }
        }

        System.out.println(req.queryParams("categoryFilter"));

        Map params = new HashMap<>();
        params.put("categories", productCategoryDataStore.getAll());
        params.put("suppliers", supplierDataStore.getAll());
        params.put("products", products);
        return new ModelAndView(params, "product/products");
    }


}
