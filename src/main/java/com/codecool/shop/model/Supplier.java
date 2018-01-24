package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * This is the Supplier class based on BaseModel.
 * <p>The purpose of this class is to be able to work with the suppliers (as objects)</p>
 * <p>In this version you can only make a supplier object.</p>
 */
public class Supplier extends BaseModel {

    private static final Logger logger = LoggerFactory.getLogger(Product.class);


    private String description;

    /**
     * Constructor, you can set the name and the description.
     * @param name the supplier's name (declared in BaseModel)
     * @param description information about the supplier (declared in BaseModel)
     */
    public Supplier(String name, String description) {
        super(name);
        this.description = description;
        logger.info("Supplier created with name:{} , description:{}",name,description);
    }

    /**
     * @return the supplier's name, id and description in printable format
     */
    public String toString() {
        return String.format("id: %1$d, " +
                        "name: %2$s, " +
                        "description: %3$s",
                this.id,
                this.name,
                this.description
        );
    }
}