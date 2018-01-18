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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProductDaoDB implements ProductDao {

    private static ProductDaoDB instance = null;

    ProductCategoryDaoDB productCategoryDaoDB = ProductCategoryDaoDB.getInstance();
    SupplierDaoDB supplierDaoDB = SupplierDaoDB.getInstance();

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoDB.class);

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

            String sqlStatement = "INSERT INTO products (name, description, defaultprice, currency, productcategory_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().toString());
            statement.setInt(5, product.getProductCategory().getId());
            statement.setInt(6, product.getSupplier().getId());
            statement.execute();
            logger.info(" {} is added to Product List", product);

        } catch (SQLException e) {
            logger.warn("Invalid Product category or null");
            throw new IllegalArgumentException("Invalid Product category or null", e);
        }
    }

    @Override
    public Product find(int id) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM products WHERE id =  ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
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
                product.setId(id);
                logger.info("Product with Id:{} is found",id);
                return product;
            }
            logger.warn("There is no product with id:{}",id);
            throw new IllegalArgumentException("Invalid id");

        } catch (SQLException e) {
            logger.error("Connection is not working");
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
            logger.info("Product with Id:{} is is removed",id);

        } catch (SQLException e) {
            logger.warn("Invalid Product category or null");
            throw new IllegalArgumentException("Invalid Product category or null", e);
        }
    }

    @Override
    public List<Product> getAll() {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT id FROM products";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {

                Product product = this.find(resultSet.getInt("id"));
                productList.add(product);
            }
            logger.info("Product list returned successfully");
            return productList;

        } catch (SQLException e) {
            logger.warn("Invalid id");
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
            ResultSet resultSet = statement.executeQuery();

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
            logger.info("Product list from {} returned successfully",supplier);
            return productList;

        } catch (SQLException e) {
            logger.warn("Invalid id");
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
            ResultSet resultSet = statement.executeQuery();

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
            logger.info("Product list with {} returned successfully",productCategory);
            return productList;

        } catch (SQLException e) {
            logger.warn("Invalid id");
            throw new IllegalArgumentException("Invalid id");
        }
    }
}
