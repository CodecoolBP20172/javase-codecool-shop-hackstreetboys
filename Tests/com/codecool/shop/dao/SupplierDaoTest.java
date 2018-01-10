package com.codecool.shop.dao;

import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        instance.getAll().clear();

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
        int previousSize = instance.getAll().size();
        instance.add(null);
        int nextSize = instance.getAll().size();
        assertEquals(previousSize, nextSize);
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
    public void testGetAll() {
        assertEquals(3, instance.getAll().size());
    }
}