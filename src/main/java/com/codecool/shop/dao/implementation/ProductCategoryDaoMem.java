package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ProductCategoryDaoMem implements ProductCategoryDao {

    private List<ProductCategory> DATA = new ArrayList<>();
    private static ProductCategoryDaoMem instance = null;
    private AtomicInteger maxId = new AtomicInteger(1);

    private static final Logger logger = LoggerFactory.getLogger(OrderDaoMem.class);



    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductCategoryDaoMem() {
    }

    public static ProductCategoryDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoMem();
            logger.info("ProductCategoryDaoMem singleton is initialized");
        }
        logger.info(" {} returned as a ProductCategoryDaoMem singleton", instance);
        return instance;
    }

    @Override
    public void add(ProductCategory category) {
        if (category != null){
            category.setId(maxId.getAndIncrement());
            DATA.add(category);
            logger.info("{} category added to DATA (List of productCategories)", category);
        }
        else {
            logger.warn("Product which you wanted to add is null");
            throw new NullPointerException();
        }
    }


    @Override
    public ProductCategory find(int id) {
        if (id <= maxId.intValue() && id >= 1 ) {
            ProductCategory foundProductCategory =  DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
            logger.info("{} found for id: {}", foundProductCategory, id);
        } else {
            logger.warn("No ProductCategory found with id: {}", id);
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void remove(int id) {
        logger.info("Product with Id:{} is is removed",id);
        DATA.remove(find(id));
    }

    @Override
    public List<ProductCategory> getAll() {
        return DATA;
    }
}
