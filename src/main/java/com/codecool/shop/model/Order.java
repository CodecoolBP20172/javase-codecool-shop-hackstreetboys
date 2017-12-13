package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.OrderDaoMem;

import java.util.HashMap;

public class Order extends BaseModel {


    private HashMap<Product, Integer> products = new HashMap<>();
    private static Order instance = null;
    private Integer userId;
    private Integer numberOfProducts = 0;


    public Order(Integer userId) {
        super("order");
        this.userId = userId;
    }


    public void add(Product product) {
        if (products.get(product) == null) {
            products.put(product, 1);
        } else {
            products.put(product, products.get(product) + 1);
        }
        numberOfProducts++;
    }

    public void remove(int id) { products.remove(find(id)); }

    public Product find(int id)  {
        for (Product product : products.keySet()) {
            if (product.id == id) {
                return product;
            }
        }
        return null;
    }

    public HashMap<Product, Integer> getAll() { return products; }

    public Integer getUserId() { return userId; }

    public Integer getNumberOfProducts() { return numberOfProducts; }

}
