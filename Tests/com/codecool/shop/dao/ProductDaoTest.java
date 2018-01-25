package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public abstract class ProductDaoTest<T extends ProductDao> {

    private T instance;

    ProductCategory testCategoryToFillUpData = new ProductCategory("testCat", "ppk", "sdk");

    ProductCategory testCategory2ToFillUpData = new ProductCategory("testCat2", "ppk", "sdk");

    Supplier testSupplierToFillUpData = new Supplier("testSup", "plop");
    Supplier testSupplier2ToFillUpData = new Supplier("testSup2", "plop");

    Product testProduct1ToFillUpData = new Product("testProduct1ToFillUpData", 10, "USD", "Shit", testCategoryToFillUpData, testSupplierToFillUpData);
    Product testProduct2ToFillUpData = new Product("testProduct2ToFillUpData", 100, "USD", "a", testCategory2ToFillUpData, testSupplierToFillUpData);
    Product testProduct3ToFillUpData = new Product("testProduct3ToFillUpData", 1000, "USD", "brick", testCategoryToFillUpData, testSupplierToFillUpData);
    Product testProduct4ToFillUpData = new Product("testProduct4ToFillUpData", 10000, "USD", "sick", testCategoryToFillUpData, testSupplier2ToFillUpData);

    Supplier supplierToTestAddMethod = new Supplier("testsupplier1", "something");

    ProductCategory prodCatToTestAddMethod = new ProductCategory("testtablet", "sc", "src");
    Product productToTestAddMethod = new Product("test", 10, "USD", "testscription", prodCatToTestAddMethod, supplierToTestAddMethod);


    protected abstract T createInstance();


    @BeforeEach
    public void testSetup() {
        instance = createInstance();
        try {
            List<Product> productsToDel = new ArrayList<Product>(instance.getAll());

            for (Product product : productsToDel) {
                int idToDel = product.getId();
                instance.remove(idToDel);
            }

            instance.add(testProduct1ToFillUpData);
            testProduct1ToFillUpData.setId(1);
            instance.add(testProduct2ToFillUpData);
            testProduct2ToFillUpData.setId(2);
            instance.add(testProduct3ToFillUpData);
            testProduct3ToFillUpData.setId(3);
            instance.add(testProduct4ToFillUpData);
            testProduct4ToFillUpData.setId(5);

        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetAllisWorking() {
        assertTrue(true);
    }


    @Test
    public void testAdd() {
        int previousSize = 0;
        try {
            previousSize = instance.getAll().size();
            instance.add(productToTestAddMethod);
            int nextSize = instance.getAll().size();
            assertEquals(previousSize + 1, nextSize);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAddNull() {
        assertThrows(NullPointerException.class, () -> {
            instance.add(null);
        });
    }


    @Test
    public void testFind() {
        try {
            assertTrue(instance.find(1) != null);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    ;


    @Test
    public void testFindWrongId() {
        assertThrows(IllegalArgumentException.class, () -> {
            instance.find(-6);
            instance.find(1000);
        });
    }


    @Test
    public void testFindNotFound() {
        try {
            assertEquals(null, instance.find(4));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetAll() {
        try {
            assertEquals(4, instance.getAll().size());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    ;

    @Test
    public void testGetBySup() {
        try {
            assertEquals(testProduct4ToFillUpData.toString(), instance.getBy(testSupplier2ToFillUpData).get(0).toString());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetByProdCat() {
        try {
            assertEquals(testProduct1ToFillUpData.toString(), instance.getBy(testCategoryToFillUpData).get(0).toString());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetBySpecProdCat() {
        try {
            assertEquals(testProduct2ToFillUpData.toString(), instance.getBy(testCategory2ToFillUpData).get(0).toString());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}