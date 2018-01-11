package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.db.ConnectionHandler;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ProductDaoDB implements ProductDao {

    private static ProductDaoDB instance = null;

    ProductCategoryDaoDB productCategoryDaoDB = ProductCategoryDaoDB.getInstance();
    SupplierDaoDB supplierDaoDB = SupplierDaoDB.getInstance();

    private ProductDaoDB() {
    }

    public static ProductDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Product product) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "INSERT INTO products (id, name, description, defaultprice, currency, productcategory_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setString(3, product.getDescription());
            statement.setFloat(4, product.getDefaultPrice());
            statement.setString(5, product.getDefaultCurrency().toString());
            statement.setInt(6, product.getProductCategory().getId());
            statement.setInt(7, product.getSupplier().getId());
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid Product category or null", e);
        }
    }

    @Override
    public Product find(int id) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM products WHERE id =  ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery(sqlStatement);
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("defaultprice");
                String currency = resultSet.getString("currency");
                String description = resultSet.getString("description");
                Integer productcategoryNumber = resultSet.getInt("productcategory_id");
                Integer supplierNumber = resultSet.getInt("supplier_id");
                ProductCategory productCategory = productCategoryDaoDB.find(productcategoryNumber);
                Supplier supplier = supplierDaoDB.find(supplierNumber);

                Product product = new Product(name, price, currency, description, productCategory, supplier);
                return product;
            }
            throw new IllegalArgumentException("Invalid id");

        } catch (SQLException e) {
            throw new IllegalArgumentException("Connection is not working", e);
        }
    }


    @Override
    public void remove(int id) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "DELETE FROM products WHERE id = ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid Product category or null", e);
        }
    }

    @Override
    public List<Product> getAll() {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM products";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("defaultprice");
                String currency = resultSet.getString("currency");
                String description = resultSet.getString("description");
                Integer productcategoryNumber = resultSet.getInt("productcategory_id");
                Integer supplierNumber = resultSet.getInt("supplier_id");
                ProductCategory productCategory = productCategoryDaoDB.find(productcategoryNumber);
                Supplier supplier = supplierDaoDB.find(supplierNumber);

                Product product = new Product(name, price, currency, description, productCategory, supplier);
                productList.add(product);
            }
            return productList;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid id");
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM products WHERE id = ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, supplier.getId());
            statement.execute();

            statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("defaultprice");
                String currency = resultSet.getString("currency");
                String description = resultSet.getString("description");
                Integer productcategoryNumber = resultSet.getInt("productcategory_id");
                ProductCategory productCategory = productCategoryDaoDB.find(productcategoryNumber);

                Product product = new Product(name, price, currency, description, productCategory, supplier);
                productList.add(product);
            }
            return productList;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid id");
        }
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM products WHERE id = ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, productCategory.getId());
            statement.execute();

            statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {

                String name = resultSet.getString("name");
                Float price = resultSet.getFloat("defaultprice");
                String currency = resultSet.getString("currency");
                String description = resultSet.getString("description");
                Integer supplierNumber = resultSet.getInt("supplier_id");
                Supplier supplier = supplierDaoDB.find(supplierNumber);

                Product product = new Product(name, price, currency, description, productCategory, supplier);
                productList.add(product);
            }
            return productList;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid id");
        }
    }
}
