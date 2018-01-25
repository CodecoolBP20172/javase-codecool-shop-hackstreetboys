package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.SupplierDaoMem;

import java.util.ArrayList;

/**
 * This is the Supplier class based on BaseModel.
 * <p>The purpose of this class is to be able to work with the suppliers (as objects)</p>
 * <p>In this version you can only make a supplier object.</p>
 */
public class Supplier extends BaseModel {


    private String description;

    /**
     * Constructor, you can set the name and the description.
     *
     * @param name        the supplier's name (declared in BaseModel)
     * @param description information about the supplier (declared in BaseModel)
     */
    public Supplier(String name, String description) {
        super(name);
        this.description = description;
    }

    /**
     * @return the supplier's name, id and description in printable format
     */
    public String toString() {
        return String.format("id: %1$d, " + "name: %2$s, " + "description: %3$s", this.id, this.name, this.description);
    }
}