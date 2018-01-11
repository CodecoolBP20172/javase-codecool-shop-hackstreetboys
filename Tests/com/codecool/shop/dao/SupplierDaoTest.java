package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public abstract class SupplierDaoTest <T extends SupplierDao> {

    private T instance;

    Supplier testSupplier1ToFillUpData = new Supplier("testSup","plop");
    Supplier testSupplier2ToFillUpData = new Supplier("testSup2","ploppp");
    Supplier testSupplier3ToFillUpData = new Supplier("testSup3","ploppp");
    Supplier supplierToTestAddMethod = new Supplier("testsupplier1", "something");

    protected abstract T createInstance();


    @BeforeEach
    public void testSetup() {
        instance = createInstance();

        List<Supplier> suppliersToDel = new ArrayList<Supplier>(instance.getAll());

        for (Supplier supplier : suppliersToDel) {
            int idToDel = supplier.getId();
            instance.remove(idToDel);
        }

        instance.add(testSupplier1ToFillUpData);
        instance.add(testSupplier2ToFillUpData);
        instance.add(testSupplier3ToFillUpData);
    }


    @Test
    public  void testAdd() {
        int previousSize = instance.getAll().size();
        instance.add(supplierToTestAddMethod);
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

    @Test
    public void testFindWrongId2(){
        assertThrows(IllegalArgumentException.class, () -> {
            instance.find(1000);
        });
    }


    @Test
    public void testGetAll() {
        assertEquals(3, instance.getAll().size());
    }
}