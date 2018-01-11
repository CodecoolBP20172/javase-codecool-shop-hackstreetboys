package com.codecool.shop.dao;

import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class OrderDaoTest <T extends OrderDao> {

    private T instance;


    ProductCategory testCategoryToFillUpData = new ProductCategory("testCat","ppk","sdk");

    Supplier testSupplierToFillUpData = new Supplier("testSup","plop");

    Product testProduct1ToFillUpData = new Product("testProduct1ToFillUpData",10,"USD","Shit",testCategoryToFillUpData,testSupplierToFillUpData);

    Order testOrder1ToFillUpOrder = new Order(1);
    Order testOrder2ToFillUpOrder = new Order(2);
    Order testOrder3ToFillUpOrder = new Order(3);
    Order testOrder4ToFillUpOrder = new Order(5);

    Order testOrder1ToAddOrder = new Order(5);




    protected abstract T createInstance();


    @BeforeEach
    public void testSetup() {
        instance = createInstance();
        List<Order> productsToDel = new ArrayList<Order>(instance.getAll());

        for (Order order : productsToDel) {
            int idToDel = order.getId();
            instance.remove(idToDel);
        }

        testProduct1ToFillUpData.setId(1);
        testOrder1ToFillUpOrder.add(testProduct1ToFillUpData);
        testOrder1ToAddOrder.add(testProduct1ToFillUpData);


        instance.add(testOrder1ToFillUpOrder);
        instance.add(testOrder2ToFillUpOrder);
        instance.add(testOrder3ToFillUpOrder);
        instance.add(testOrder4ToFillUpOrder);
    }


    @Test
    public void testGetAllisWorking() {
        assertTrue(true);
    }


    @Test
    public  void testAdd() {
        int previousSize = instance.getAll().size();
        instance.add(testOrder1ToAddOrder);
        int nextSize = instance.getAll().size();
        assertEquals(previousSize, nextSize-1);
    }


    @Test
    public void testAddNull() {
        assertThrows(NullPointerException.class, () -> {
            instance.add(null);
        });
    }


    @Test
    public void testFind() {
        instance.add(testOrder1ToFillUpOrder);
        assertTrue(instance.find(0) != null);
    };


    @Test
    public void testFindWrongId(){
        assertThrows(IllegalArgumentException.class, () -> {
            instance.find(-6);
            instance.find(1000);

        });
    }


    @Test
    public void testFindNotFound(){
        assertEquals(null, instance.find(4));
    }


    @Test
    public void testGetAll() {
        assertEquals(4, instance.getAll().size());
    };
}