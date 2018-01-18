package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;


public class Supplier extends BaseModel {

    private static final Logger logger = LoggerFactory.getLogger(Product.class);


    public Supplier(String name, String description) {
        super(name);
        this.description = description;
        logger.info("Supplier created with name:{} , description:{}",name,description);
    }

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