package com.codecool.shop.dao.implementation;


import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDaoMem implements ProductDao {

    private List<Product> DATA = new ArrayList<>();
    private static ProductDaoMem instance = null;

    /* A private Constructor prevents any other class from instantiating.
     */
    private ProductDaoMem() {
    }

    public static ProductDaoMem getInstance() {
        if (instance == null) {
            instance = new ProductDaoMem();
        }
        return instance;
    }

    public ArrayList<Integer> minMaxId(){
        ArrayList<Integer> maxMinId = new ArrayList<>();
        ArrayList <Integer> idList = new ArrayList();
        if (DATA.size() < 1) {
            return idList.add()
        }
        for (Product product:DATA) {
            idList.add(product.getId());
        }
        System.out.println(idList);
        System.out.println(Collections.min(idList));
        maxMinId.add(Collections.min(idList));
        maxMinId.add(Collections.max(idList));
        System.out.println(maxMinId);
        return maxMinId;
    }

    @Override
    public void add(Product product) {
        if(product !=null){
            product.setId(minMaxId().get(1) + 1);
            DATA.add(product);
        }
        else{
            throw new NullPointerException();
        }
    }

    @Override
    public Product find(int id) {
        if (id >= minMaxId().get(0) && id <= minMaxId().get(1)) {
            return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void remove(int id) {
        DATA.remove(find(id));
    }

    @Override
    public List<Product> getAll() {
        return DATA;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return DATA.stream().filter(t -> t.getSupplier().equals(supplier)).collect(Collectors.toList());
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return DATA.stream().filter(t -> t.getProductCategory().equals(productCategory)).collect(Collectors.toList());
    }
}
