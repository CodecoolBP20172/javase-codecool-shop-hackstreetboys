package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;


public class ProductDaoMemTest extends ProductDaoTest<ProductDaoMem> {



    @Override
    protected ProductDaoMem createInstance() {
        return ProductDaoMem.getInstance();
    }






}
