package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * This is the implementation of the ProductDao interface what provides access to memory.
 * <p>Here we save the products in an ArrayList</p>
 * <p>For more info please check: {@link ProductDao}</p>
 */
public class ProductDaoMem implements ProductDao {

    private List<Product> DATA = new ArrayList<>();
    private static ProductDaoMem instance = null;
    private AtomicInteger maxId = new AtomicInteger(1);


    /**
     * Constructor. Private with the purpose of preventing any other class from instantiating
     */
    private ProductDaoMem() {
    }

    /**
     * This method returns an instance if doesn't exist. Otherwise returns the existing instance.
     *
     * @return instance of ProductDaoMem
     */
    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }


    /**
     * Implementation of the ProductDao interface add(Product product) method.
     */
    @Override
    public void add(Product product) {
        if (product != null) {
            product.setId(maxId.getAndIncrement());
            DATA.add(product);
        } else {
            throw new NullPointerException();
        }
    }


    /**
     * Implementation of the ProductDao interface find(int id) method.
     */
    @Override
    public Product find(int id) {
        if (id <= maxId.intValue() && id >= 1) {
            return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Implementation of ProductDao interface remove(int id) method.
     */
    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    /**
     * Implementation of ProductDao interface getAll() method.
     */
    @Override
    public List<Product> getAll() {
        return DATA;
    }

    /**
     * Implementation of ProductDao interface getBy(Supplier supplier) method
     */
    @Override
    public List<Product> getBy(Supplier supplier) {
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    /**
     * Implementation of ProductDao interface getBy(ProductCategory productCategory) method)
     */
    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }

}
