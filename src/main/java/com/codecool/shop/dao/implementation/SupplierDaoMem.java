package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SupplierDaoMem implements SupplierDao {

    private List<Supplier> DATA = new ArrayList<>();
    private static SupplierDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoMem() {
    }

    public static SupplierDaoMem getInstance() {
        if (instance == null) {
            instance = new SupplierDaoMem();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        if ( supplier != null){
            supplier.setId(DATA.size() + 1);
            DATA.add(supplier);
        }
        else{
            throw new NullPointerException();
        }
    }

    @Override
    public Supplier find(int id) {
        ArrayList <Integer> maxMinId = new ArrayList();
        for (Supplier supplier:DATA) {
            maxMinId.add(supplier.getId());
        }
        if (id >= Collections.min(maxMinId) && id <= Collections.max(maxMinId)) {
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
    public List<Supplier> getAll() {
        return DATA;
    }
}
