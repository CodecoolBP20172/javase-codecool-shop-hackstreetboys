package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;

import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductCategory extends BaseModel {
    private String department;
    private static final Logger logger = LoggerFactory.getLogger(ProductCategory.class);

    public ProductCategory(String name, String department, String description) {
        super(name);
        this.department = department;
        logger.info("Product Category created with {} ,{} ,{}",name,department,description);
    }

    public String getDepartment() {
        logger.info("Product Category with department:{}",department);
        return department;
    }

    public void setDepartment(String department) {
        logger.info("Product Category department set to {}",department);
        this.department = department;
    }


    public String toString() {
        return String.format(
                "id: %1$d," +
                        "name: %2$s, " +
                        "department: %3$s, " +
                        "description: %4$s",
                this.id,
                this.name,
                this.department,
                this.description);
    }
}