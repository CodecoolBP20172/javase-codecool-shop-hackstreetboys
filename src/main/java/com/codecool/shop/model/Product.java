package com.codecool.shop.model;

import java.util.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Product extends BaseModel {

    private float defaultPrice;
    private Currency defaultCurrency;
    private ProductCategory productCategory;
    private Supplier supplier;
    private static final Logger logger = LoggerFactory.getLogger(Product.class);


    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
        logger.info("Product is created with {},{},{},{},{},{}",name,defaultPrice,currencyString,description,productCategory,supplier);
    }

    public float getDefaultPrice() {
        logger.info("Default price for product {} is {}",name,defaultPrice);
        return defaultPrice; }

    public void setDefaultPrice(float defaultPrice) {
        logger.info("Default price for product {} is {}",name,defaultPrice);
        this.defaultPrice = defaultPrice;
    }

    public Currency getDefaultCurrency() {
        logger.info("Currency for product {} is {}",name,defaultCurrency);
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        logger.info("Price for product {} is {}",name,defaultCurrency);
        this.defaultCurrency = defaultCurrency;
    }

    public String getPrice() {
        logger.info("Price for product {} is {} {}",name,defaultPrice,defaultCurrency);
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    public void setPrice(float price, String currency) {
        logger.info("Price for product {} is {} {}",name,defaultPrice,defaultCurrency);
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    public ProductCategory getProductCategory() {
        logger.info("Product category for product {} is {}",name,productCategory);
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        logger.info("Product category for product {} is {}",name,productCategory);
        this.productCategory = productCategory;
    }

    public Supplier getSupplier() {
        logger.info("Supplier for product {} is {}",name,supplier);
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        logger.info("Supplier for product {} is {}",name,supplier);
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "defaultPrice: %3$f, " +
                        "defaultCurrency: %4$s, " +
                        "productCategory: %5$s, " +
                        "supplier: %6$s",
                this.id,
                this.name,
                this.defaultPrice,
                this.defaultCurrency.toString(),
                this.productCategory.getName(),
                this.supplier.getName());
    }
}
