package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface is part of the dao, furthermore it is responsible for the webshop's product categories.
 * <p>It provides access to the database or the other persistence storage (memory).</p>
 * (It depends on the implementing class.)
 * <p>Here you can add / find / remove product categories and also you can access all of them.</p>
 */
public interface ProductCategoryDao {

    /**
     * Method signature of adding product category
     * @param category this is the product category you would like to add
     */
    void add(ProductCategory category);

    /**
     * Method signature of finding a product category
     * @param id unique identifier that belongs to one specific product category what you would like to find
     * @return the product category which belongs to that unique id
     */
    ProductCategory find(int id);

    /**
     * Method signature of removing a product category
     * @param id unique identifier that belongs to one specific product category what you would like to delete
     */
    void remove(int id);

    /**
     * Method signature of accessing all of the product categories
     * @return all product categories
     */
    List<ProductCategory> getAll();

}
