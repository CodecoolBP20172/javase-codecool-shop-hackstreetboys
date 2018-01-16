package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.SupplierDaoMem;

import java.util.ArrayList;


public class Supplier extends BaseModel {


    public Supplier(String name, String description) {

        super(name);
        this.description = description;
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