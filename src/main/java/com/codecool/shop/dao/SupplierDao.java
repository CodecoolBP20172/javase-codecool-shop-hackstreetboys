package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;

import java.sql.SQLException;
import java.util.List;

/**
 * This interface is part of the dao, furthermore it is responsible for the webshop's suppliers.
 * <p>It provides access to the database or the other persistence storage (memory).
 * (It depends on the implementing class)</p>
 * <p>Here you can add / find / remove products and also you can access all of them.</p>
 */
public interface SupplierDao {

    void add(Supplier supplier) throws SQLException;
    Supplier find(int id) throws SQLException;
    void remove(int id) throws SQLException;
    List<Supplier> getAll() throws SQLException;
  
    /**
     * Method signature of adding suppliers.
     * @param supplier this is the supplier you would like to add
     */
    void add(Supplier supplier);

    /**
     * Method signature of finding suppliers
     * @param id unique identifier that belongs to one specific supplier what you would like to find
     * @return the supplier which belongs to that unique id
     */
    Supplier find(int id);

    /**
     * Method signature of removing suppliers
     * @param id unique identifier that belongs to one specific supplier what you would like to remove
     */
    void remove(int id);

    /**
     * Method signature of accessing all suppliers
     * @return all of the suppliers
     */
    List<Supplier> getAll();
}
