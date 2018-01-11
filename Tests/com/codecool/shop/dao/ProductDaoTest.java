package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ProductDaoTest <T extends ProductDao> {

    private T instance;


    ProductCategory testCategoryToFillUpData = new ProductCategory("testCat","ppk","sdk");
    ProductCategory testCategory2ToFillUpData = new ProductCategory("testCat2","ppk","sdk");

    Supplier testSupplierToFillUpData = new Supplier("testSup","plop");
    Supplier testSupplier2ToFillUpData = new Supplier("testSup2","plop");

    Product testProduct1ToFillUpData = new Product("testProduct1ToFillUpData",10,"USD","Shit",testCategoryToFillUpData,testSupplierToFillUpData);
    Product testProduct2ToFillUpData = new Product("testProduct2ToFillUpData",100,"USD","a",testCategory2ToFillUpData,testSupplierToFillUpData);
    Product testProduct3ToFillUpData = new Product("testProduct3ToFillUpData",1000,"USD","brick",testCategoryToFillUpData,testSupplierToFillUpData);
    Product testProduct4ToFillUpData = new Product("testProduct4ToFillUpData",10000,"USD","sick",testCategoryToFillUpData,testSupplier2ToFillUpData);





    Supplier supplierToTestAddMethod = new Supplier("testsupplier1", "something");
    ProductCategory prodCatToTestAddMethod = new ProductCategory("testtablet", "sc","src");
    Product productToTestAddMethod = new Product("test", 10, "USD", "testscription", prodCatToTestAddMethod, supplierToTestAddMethod);



    protected abstract T createInstance();


    @BeforeEach
    public void testSetup() {
        ArrayList<Product> fick = new ArrayList();
        instance = createInstance();
        //instance.getAll().clear();
        for(Product product:instance.getAll()){
            fick.add(product);
        }
        for (Product product : fick) {
            int idToDel = product.getId(); //id
            instance.remove(idToDel);
        }
        System.out.println(instance.getAll());

        instance.add(testProduct1ToFillUpData);
        instance.add(testProduct2ToFillUpData);
        instance.add(testProduct3ToFillUpData);
        instance.add(testProduct4ToFillUpData);
    }


    @Test
    public void testGetAllisWorking() {
        assertTrue(true);
    }


    @Test
    public  void testAdd() {
        int previousSize = instance.getAll().size();
        instance.add(productToTestAddMethod);
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
    };

    @Test
    public void testFindWrongId(){
        System.out.println(instance.getAll());
        assertThrows(IllegalArgumentException.class, () -> {
            instance.find(-6);
        });
    }

    @Test void testFindWrongId2(){
        assertThrows(IllegalArgumentException.class, () -> {
            instance.find(100);
        });
    }


    @Test
    public void testGetAll() {
        assertEquals(4, instance.getAll().size());
    };

    @Test
    public void testGetBySup(){
        assertEquals(testProduct4ToFillUpData.toString(), instance.getBy(testSupplier2ToFillUpData).get(0).toString());
    }

    @Test
    public void testGetByProdCat(){
        assertEquals(testProduct1ToFillUpData.toString(), instance.getBy(testCategoryToFillUpData).get(0).toString());
    }

    @Test
    public void testGetBySpecProdCat(){
        assertEquals(testProduct2ToFillUpData.toString(),instance.getBy(testCategory2ToFillUpData).get(0).toString());
    }


}