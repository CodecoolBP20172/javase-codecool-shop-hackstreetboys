package com.codecool.shop.model;

import java.util.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
    private static final Logger logger = LoggerFactory.getLogger(Product.class);


    /**
     * Constructor, set the name, defaultPrice, currency, description, productCategory and supplier of the Product
     * @param name name of the actual product
     * @param defaultPrice the selling price
     * @param currencyString the currency of the product
     * @param description description about the product
     * @param productCategory products grouped by some properties. One product category covers more products
     * @param supplier the supplier of the actual product
     */
    public Product(String name, float defaultPrice, String currencyString, String description, ProductCategory productCategory, Supplier supplier) {
        super(name, description);
        this.setPrice(defaultPrice, currencyString);
        this.setSupplier(supplier);
        this.setProductCategory(productCategory);
        logger.info("Product is created with {},{},{},{},{},{}",name,defaultPrice,currencyString,description,productCategory,supplier);
    }


    /**
     * @return defaultPrice of the actual product
     */
    public float getDefaultPrice() {
        logger.info("Default price for product {} is {}",name,defaultPrice);
        return defaultPrice; }

    public void setDefaultPrice(float defaultPrice) {
        logger.info("Default price for product {} is {}",name,defaultPrice);
        this.defaultPrice = defaultPrice;
    }

    /**
     * @return the defaultCurrency of the actual product
     */
    public Currency getDefaultCurrency() {
        logger.info("Currency for product {} is {}",name,defaultCurrency);
        return defaultCurrency;
    }

    public void setDefaultCurrency(Currency defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
        logger.info("Price for product {} is {}",name,this.defaultCurrency);
    }

    public String getPrice() {
        logger.info("Price for product {} is {} {}",name,defaultPrice,defaultCurrency);
        return String.valueOf(this.defaultPrice) + " " + this.defaultCurrency.toString();
    }

    /**
     * Set the price and the related currency of the actual product
     * @param price
     * @param currency
     */
    public void setPrice(float price, String currency) {
        logger.info("Price for product {} is {} {}",name,defaultPrice,defaultCurrency);
        this.defaultPrice = price;
        this.defaultCurrency = Currency.getInstance(currency);
    }

    /**
     * @return the productCategory of the actual product
     */
    public ProductCategory getProductCategory() {
        logger.info("Product category for product {} is {}",name,productCategory);
        return productCategory;
    }

    /**
     * Set the productCategory of the actual product.
     * @param productCategory
     */
    public void setProductCategory(ProductCategory productCategory) {
        logger.info("Product category for product {} is {}",name,productCategory);
        this.productCategory = productCategory;
    }

    /**
     * @return the supplier of the actual product
     */
    public Supplier getSupplier() {
        logger.info("Supplier for product {} is {}",name,supplier);
        return supplier;
    }

    /**
     * Set the supplier of the actual product.
     * @param supplier
     */
    public void setSupplier(Supplier supplier) {
        logger.info("Supplier for product {} is {}",name,supplier);
        this.supplier = supplier;
    }

    /**
     * @return the product's name, defaultPrice, defaultCurrency, productCategory and supplier
     * in printable format
     */
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
