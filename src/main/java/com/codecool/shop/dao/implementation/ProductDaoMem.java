package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDaoMem implements ProductDao {

    private List<Product> DATA = new ArrayList<>();
    private static ProductDaoMem instance = null;
    private AtomicInteger maxId = new AtomicInteger(1);
    private static final Logger logger = LoggerFactory.getLogger(ProductDaoMem.class);


    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        if(product !=null){
            product.setId(maxId.getAndIncrement());
            DATA.add(product);
            logger.info(" {} is added to DATA (Product List)", product);
        }
        else {
            logger.warn("Added product is null");
            throw new NullPointerException();
        }
    }

    @Override
    public Product find(int id) {
        if (id <= maxId.intValue() && id >= 1 ) {
            logger.info("Product with Id:{} is found",id);
            return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        } else {
            logger.warn("There is no product with id:{}",id);
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
        logger.info("Product with Id:{} is is removed",id);
    }

    @Override
    public List<Product> getAll() {
        logger.info("DATA (product list) returned successfully");
        return DATA;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        logger.info("Product list from {} returned successfully",supplier);
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        logger.info("Product list with {} returned successfully",productCategory);
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

}
