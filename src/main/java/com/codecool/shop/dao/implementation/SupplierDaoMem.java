package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DaoException;
import com.codecool.shop.dao.DaoRecordNotFoundException;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * This is a SupplierDaoMem class, which implements SupplierDao Interface.
 * <p>The purpose of this class is to store Suppliers into an ArrayList in the memory</p>
 */
public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> DATA = new ArrayList<>();
    private static SupplierDaoMem instance = null;
    private AtomicInteger maxId = new AtomicInteger(1);


    /**
     * A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    /**
     * Creates a Singleton instance.
     *
     * @return the same SupplierDaoMem instance everytime.
     */
    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    /**
     * Add Supplier object to DATA.
     * <p>DATA is an ArrayList, which stores Supplier objects.</p>
     *
     * @param supplier - is the supplier object, which we want to add to DATA.
     */
    @Override
    public void add(Supplier supplier) throws DaoException {
        if (supplier != null) {
            supplier.setId(maxId.getAndIncrement());
            DATA.add(supplier);
        } else {
            throw new DaoException("tried to add null");
        }
    }

    /**
     * Find Supplier object by it's id in DATA.
     * <p>DATA is an ArrayList, which stores Supplier objects.</p>
     *
     * @param id - id of the Supplier object.
     * @return the Supplier object.
     * @throws IllegalArgumentException if id is invalid
     */
    @Override
    public Supplier find(int id) throws DaoException {
        if (id <= maxId.intValue() && id >= 1) {
            return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        } else {
            throw new DaoRecordNotFoundException("couldn't find supplier with id " + id);
        }
    }

    /**
     * Remove Supplier object by it's id from DATA.
     * <p>DATA is an ArrayList, which stores Supplier objects.</p>
     *
     * @param id - is the id of the Supplier object.
     */
    @Override
    public void remove(int id) throws DaoException {
        DATA.remove(find(id));
    }

    /**
     * Gets all Supplier object in DATA.
     *
     * @return DATA, which stores all Supplier objects.
     */
    @Override
    public List<Supplier> getAll() {
        return DATA;
    }
}
