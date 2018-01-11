package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductCategoryDaoTest;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoMemTest extends ProductCategoryDaoTest <ProductCategoryDaoMem> {


    @Override
    protected ProductCategoryDaoMem createInstance() {
        return ProductCategoryDaoMem.getInstance();
    }
}