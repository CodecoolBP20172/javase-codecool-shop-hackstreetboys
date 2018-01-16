package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDaoTest;


public class SupplierDaoMemTest extends SupplierDaoTest<SupplierDaoMem> {

    @Override
    protected SupplierDaoMem createInstance() {
        return SupplierDaoMem.getInstance();
    }

}