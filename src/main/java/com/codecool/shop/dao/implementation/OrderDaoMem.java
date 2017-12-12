package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderDaoMem implements OrderDao {


    private List<Product> DATA = new ArrayList<>();
    private HashMap<Product, Integer> DATA2 = new HashMap<>();
    private static OrderDaoMem instance = null;

    private OrderDaoMem() {
    }

    public static OrderDaoMem getInstance() {
        if (instance == null) {
            instance = new OrderDaoMem();
        }
        return instance;
    }


    @Override
    public void add(Product product) {
        //for (int i=0; i<DATA2.size(); i++) {
        if (DATA2.get(product) == null) {
            DATA2.put(product, 1);
        } else {
            DATA2.put(product, DATA2.get(product) + 1);
        }
    }

    /*public void add(Product product) {
        //product.setId(DATA.size() + 1); szerintem ez ide nem kell
        DATA.add(product);
    }*/


    @Override
    public Product find(int id)  {
        return DATA.stream().filter(t -> t.getId() == id).findFirst().orElse(null);
    }


    @Override
    public void remove(int id) { DATA.remove(find(id)); }

    @Override
    public HashMap<Product, Integer> getAll() {
        return DATA2;
    }
    /*public List<Product> getAll() {
        return DATA;
    }*/

    /*@Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }*/
}
