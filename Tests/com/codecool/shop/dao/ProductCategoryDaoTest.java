package com.codecool.shop.dao;

import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ProductCategoryDaoTest <T extends ProductCategoryDao> {

    private T instance;

    ProductCategory testProdCat1 = new ProductCategory("headset", "testDep", "descr");
    ProductCategory testProdCat2 = new ProductCategory("USB port", "testDep2", "descr2");
    ProductCategory testProdCat3 = new ProductCategory("microUSBCharger", "testDep3", "descr3");

    ProductCategory prodCatToAdd = new ProductCategory("addThis", "addDep", "add");

    protected abstract T createInstance();



    @BeforeEach
    public void testSetup() {
        instance = createInstance();

        List<ProductCategory> prodCatsToDel = new ArrayList<ProductCategory>(instance.getAll());

        for (ProductCategory product : prodCatsToDel) {
            int idToDel = product.getId();
            instance.remove(idToDel);
        }

        instance.add(testProdCat1);
        instance.add(testProdCat2);
        instance.add(testProdCat3);
    }


    @Test
    public void testGetAllisWorking() {
        assertTrue(true);
    }


    @Test
    public  void testAdd() {
        int previousSize = instance.getAll().size();
        instance.add(prodCatToAdd);
        int nextSize = instance.getAll().size();
        assertEquals(previousSize+1, nextSize);
    }


    @Test
    public void testAddNull() {
        assertThrows(NullPointerException.class, () -> {
            instance.add(null);
        });
    }


    @Test
    public void testFind() {
        assertTrue(instance.find(1) != null);
    }


    @Test
    public void testFindWrongId(){
        assertThrows(IllegalArgumentException.class, () -> {
            instance.find(-6);
        });
    }

    @Test void testFindWrongId2(){
        assertThrows(IllegalArgumentException.class, () -> {
            instance.find(1000);
        });
    }


    @Test
    public void testGetAll() {
        assertEquals(3, instance.getAll().size());
    }
}