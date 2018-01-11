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

            String sqlStatement = "INSERT INTO productcategories (id, name, description, department) VALUES (?, ?, ?, ?);";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, category.getId());
            statement.setString(2, category.getName());
            statement.setString(3, category.getDescription());
            statement.setString(4, category.getDepartment());

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

            ResultSet resultSet = statement.executeQuery(sqlStatement);
            if (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department")
                );
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

            String sqlStatement = "DELETE FROM productcategories WHERE id = ?";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid Product category or null", e);
        }

    }

    @Override
    public List<ProductCategory> getAll() {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM productcategories";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            List<ProductCategory> resultList = new ArrayList<>();

            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getString("department")
                );
                resultList.add(productCategory);
            }
            return resultList;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid id");
        }
    }
}
