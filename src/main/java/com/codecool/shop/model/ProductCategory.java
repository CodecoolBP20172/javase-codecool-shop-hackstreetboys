package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;

import java.util.ArrayList;

/**
 * This is the ProductCategory class based on BaseModel.
 * <p>The purpose of this class is to be able to work with the product categories (as objects)</p>
 * <p>Here you can modify and access the name and the description of the productsCategory.</p>
 * <p>To be more practical: here you can get the features of each webshop-product's product category and also you can modifiy them</p>
 */
public class ProductCategory extends BaseModel {

    private String department;

    /**
     * Constructor, set the name, department and description of the ProductCategory.
     * @param name the ProductCategory's name (declared in BaseModel)
     * @param department its department, where it belongs (declared in BaseModel)
     * @param description short summary about it
     */
    public ProductCategory(String name, String department, String description) {
        super(name);
        this.department = department;
    }

    /**
     * @return the department of the ProductCategory
     */
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the ProductCategory's name, department and description in printable format
     */
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