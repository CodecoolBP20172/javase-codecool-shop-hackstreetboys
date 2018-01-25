package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.OrderDaoMem;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the Order class based on BaseModel.
 * <p>The purpose of this class is to be able to work with the orders (as objects)</p>
 * <p>Here you can access the order's products, you can add / delete / find products,
 * get the total price of the order, all products and the user id.</p>
 * <p>To be more practical: here you can get the features of the webshop's orders and also you can modifiy the
 * products of the order</p>
 */
public class Order extends BaseModel {

    private HashMap<Product, Integer> products = new HashMap<>();
    private Integer userId;
    private Integer numberOfProducts = 0;

    private Float totalPrice = 0f;


    /**
     * Constructor, set the user id
     *
     * @param userId belongs to the user, whose order it is
     */
    public Order(Integer userId) {
        super("order");
        this.userId = userId;
    }

    /**
     * You can add one product to the order
     *
     * @param product the item what you would like to add
     */
    public void add(Product product) {
        products.merge(product, 1, (a, b) -> a + b);
        numberOfProducts++;
        totalPrice += product.getDefaultPrice();
        }

    /**
     * You can remove product from the order
     *
     * @param id the id of the product what should be deleted
     */
    public void remove(int id) {
        Product productToRemove = find(id);
        products.remove(productToRemove);
        totalPrice -= productToRemove.getDefaultPrice();
    }

    /**
     * You can find a product in the order
     *
     * @param id the product's id what should be found
     * @return the product if exist, otherwise null
     */
    public Product find(int id) {
        for (Product product : products.keySet()) {
            if (product.id == id) {
                return product;
            }
        }
        return null;
    }

    /**
     * @return all of the products in the order
     */
    public HashMap<Product, Integer> getAll() {
        return products;
    }

    /**
     * @return the user id of the user whose order it is
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @return an integer, the number of products in the order
     */
    public Integer getNumberOfProducts() {
        return numberOfProducts;
    }

    /**
     * @return the total price of the order
     */
    public Float getTotalPrice() {
        return totalPrice;
    }


    public void setNumberOfProducts(Integer numberOfProducts) {
        this.numberOfProducts = numberOfProducts;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Order{" + "products=" + products + ", userId=" + userId + ", numberOfProducts=" + numberOfProducts + ", totalPrice=" + totalPrice + '}';
    }
}
