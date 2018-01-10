package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.OrderDaoMem;

import java.util.HashMap;

public class Order extends BaseModel {


    private HashMap<Product, Integer> products = new HashMap<>();
    private Integer userId;
    private Integer numberOfProducts = 0;
    private Float totalPrice = 0f;


    public Order(Integer userId) {
        super("order");
        this.userId = userId;
        OrderDaoMem.getInstance().add(this);
    }


    public void add(Product product) {
        products.merge(product, 1, (a, b) -> a + b);
        numberOfProducts++;
        totalPrice += product.getDefaultPrice();
    }

    public void remove(int id) {
        Product productToRemove = find(id);
        products.remove(productToRemove);
        totalPrice -= productToRemove.getDefaultPrice();
    }

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
