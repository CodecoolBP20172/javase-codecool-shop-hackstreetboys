package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.OrderDaoMem;

import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Order extends BaseModel {
    private static final Logger logger = LoggerFactory.getLogger(Order.class);

    private HashMap<Product, Integer> products = new HashMap<>();
    private Integer userId;
    private Integer numberOfProducts = 0;
    private Float totalPrice = 0f;


    public Order(Integer userId) {
        super("order");
        this.userId = userId;
        OrderDaoMem.getInstance().add(this);
        logger.info("Order is created to user(Id:{}) ",userId);
    }


    public void add(Product product) {
        products.merge(product, 1, (a, b) -> a + b);
        numberOfProducts++;
        totalPrice += product.getDefaultPrice();
        logger.info("Product {} is added to order",product);
    }

    public void remove(int id) {
        Product productToRemove = find(id);
        products.remove(productToRemove);
        totalPrice -= productToRemove.getDefaultPrice();
        logger.info("Product {} is removed from order",id);
    }

    public Product find(int id)  {
        for (Product product : products.keySet()) {
            if (product.id == id) {
                logger.info("Product {} is found in order",id);
                return product;
            }
        }
        logger.info("Product {} is not found in order",id);
        return null;
    }

    public HashMap<Product, Integer> getAll() {
        logger.info("Product list returned");
        return products; }

    public Integer getUserId() {
        logger.info("user id for order returned");
        return userId; }

    public Integer getNumberOfProducts() {
        logger.info("number of products for order returned");
        return numberOfProducts; }

    public Float getTotalPrice() {
        logger.info("total price for order returned");
        return totalPrice; }

}
