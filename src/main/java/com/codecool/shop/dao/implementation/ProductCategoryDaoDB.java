package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.db.ConnectionHandler;
import com.codecool.shop.model.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoDB implements ProductCategoryDao {

    private static ProductCategoryDaoDB instance = null;

    private ProductCategoryDaoDB() {
    }

    public static ProductCategoryDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoDB();
        }
        return instance;
    }

    @Override
    public void add(ProductCategory category) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "INSERT INTO productcategories (name, description, department) VALUES (?, ?, ?);";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setString(3, category.getDepartment());
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid Product category or null", e);
        }
    }

    @Override
    public ProductCategory find(int id) {
        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM productcategories WHERE id =  ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("department"));
                productCategory.setId(id);
                return productCategory;
            }
            throw new IllegalArgumentException("Invalid id");

        } catch (SQLException e) {
            throw new IllegalArgumentException("Connection is not working", e);
        }
    }

    @Override
    public void remove(int id) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement("DELETE FROM productcategories WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid Product category or null", e);
        }

    }

    @Override
    public List<ProductCategory> getAll() {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement("SELECT id FROM productcategories");
            ResultSet resultSet = statement.executeQuery();

            List<ProductCategory> resultList = new ArrayList<>();

            while (resultSet.next()) {
                ProductCategory productCategory = this.find(resultSet.getInt("id"));

                resultList.add(productCategory);
            }
            return resultList;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid Product category or null", e);
        }
    }
}
