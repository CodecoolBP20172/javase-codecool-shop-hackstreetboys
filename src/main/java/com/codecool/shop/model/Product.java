package com.codecool.shop.model;

import java.util.Currency;

/**
 * This is the Product class based on BaseModel.
 * <p>The purpose of this class is to be able to work with the products (as objects)</p>
 * <p>Here you can modify and access the default price, default currency, the product category and the supplier of the products.</p>
 * <p>To be more practical: here you can get the features of webshop products and also you can modifiy them</p>
 */
public class Product extends BaseModel {


    private float defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;

    /**
     * Constructor, set the name, defaultPrice, currency, description, productCategory and supplier of the Product
     *
     * @param name            name of the actual product
     * @param defaultPrice    the selling price
     * @param currencyString  the currency of the product
     * @param description     description about the product
     * @param productCategory products grouped by some properties. One product category covers more products
     * @param supplier        the supplier of the actual product
     */
    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
    }

    /**
     * @return defaultPrice of the actual product
     */
    public float getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(float defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    /**
     * @return the defaultCurrency of the actual product
     */
    public Currency getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    /**
     * Set the price and the related currency of the actual product
     *
     * @param price
     * @param currency
     */
    public void setPrice(float price, String currency) {
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    /**
     * @return the productCategory of the actual product
     */
    public ProductCategory getProductCategory() {
        return productCategory;
    }

    /**
     * Set the productCategory of the actual product.
     *
     * @param productCategory
     */
    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    /**
     * @return the supplier of the actual product
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * Set the supplier of the actual product.
     *
     * @param supplier
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    /**
     * @return the product's name, defaultPrice, defaultCurrency, productCategory and supplier
     * in printable format
     */
    @Override
    public String toString() {
        return String.format("id: %1$d, " + "name: %2$s, " + "defaultPrice: %3$f, " + "defaultCurrency: %4$s, " + "productCategory: %5$s, " + "supplier: %6$s", this.id, this.name, this.defaultPrice, this.defaultCurrency.toString(), this.productCategory.getName(), this.supplier.getName());
    }
}
