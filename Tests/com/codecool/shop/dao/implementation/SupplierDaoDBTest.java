package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDaoTest;


public class SupplierDaoDBTest extends SupplierDaoTest<SupplierDaoMem> {

    @Override
    protected SupplierDaoMem createInstance() {
        return SupplierDaoMem.getInstance();
    }
}
