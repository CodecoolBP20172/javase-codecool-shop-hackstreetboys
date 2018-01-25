package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.List;

/**
 * This interface is part of the dao, furthermore it is responsible for the webshop's products.
 * <p>It provides access to the database or the other persistence storage (memory).</p>
 * (It depends on the implementing class.)
 * <p>Here you can add / find / remove products and also you can access all of them
 * or the ones belongs to a specific supplier or product category.</p>
 */
public interface ProductDao {
    /**
     * Method signature of adding products
     *
     * @param product this is the product you would like to add
     */
    void add(Product product);

    /**
     * Method signature of finding products
     *
     * @param id unique identifier that belongs to one specific product what you would like to find
     * @return the product which belongs to that unique id
     */
    Product find(int id);

    /**
     * Method signature of removing products
     *
     * @param id unique identifier that belongs to one specific product what you would like to remove
     */
    void remove(int id);

    /**
     * Method signature of accessing all products
     *
     * @return all products
     */
    List<Product> getAll();

    /**
     * Method signature of accessing those products which have the same supplier
     *
     * @param supplier whose products you would like to access
     * @return the products of the specific supplier
     */
    List<Product> getBy(Supplier supplier);

    /**
     * Method signature of accessing those products which have the same product category
     *
     * @param productCategory the products you would like to access belong to this product category
     * @return the products belongs to that specific product category
     */
    List<Product> getBy(ProductCategory productCategory);
}
