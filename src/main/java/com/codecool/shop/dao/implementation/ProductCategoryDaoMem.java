package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> DATA = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;
    private AtomicInteger maxId = new AtomicInteger(1);


    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        if (category != null) {
            category.setId(maxId.getAndIncrement());
            DATA.add(category);
        } else {
            throw new NullPointerException();
        }
    }


    @Override
    public ProductCategory find(int id) {
        if (id <= maxId.intValue() && id >= 1) {
            return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return DATA;
    }
}
