package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.DaoConnectionException;
import com.codecool.shop.dao.DaoException;
import com.codecool.shop.dao.DaoRecordNotFoundException;
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
    public void add(Product product) throws DaoException {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "INSERT INTO products (name, description, defaultprice, currency, productcategory_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getDefaultPrice());
            statement.setString(4, product.getDefaultCurrency().toString());


            statement.setInt(5, product.getProductCategory().getId());
            System.out.println(product.getProductCategory());
            statement.setInt(6, product.getSupplier().getId());
            System.out.println(product.getSupplier());
            statement.execute();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Product find(int id) throws DaoException {

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
                return product;
            }
            throw new DaoRecordNotFoundException("couldn't find product with id " + id);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public void remove(int id) throws DaoException {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "DELETE FROM products WHERE id = ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);
            if (statement.executeUpdate() == 0) {
                throw new DaoRecordNotFoundException("couldn't find product with id " + id);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Product> getAll() throws DaoException{

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT id FROM products";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {

                Product product = this.find(resultSet.getInt("id"));
                productList.add(product);
            }
            if (!productList.isEmpty()) {
                return productList;
            }
            throw new DaoRecordNotFoundException("couldn't find products");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public List<Product> getBy(Supplier supplier) throws DaoException {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT id FROM products WHERE supplier_id = ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, supplier.getId());
            statement.execute();
            statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {
                Product product = find(resultSet.getInt("id"));
                productList.add(product);
            }
            if (!productList.isEmpty()) {
                return productList;
            }
            throw new DaoRecordNotFoundException("Invalid supplier");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }


    @Override
    public List<Product> getBy(ProductCategory productCategory) throws DaoException {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT id FROM products WHERE productcategory_id = ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, productCategory.getId());
            statement.execute();

            statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            List<Product> productList = new ArrayList<>();

            while (resultSet.next()) {
                Product product = find(resultSet.getInt("id"));
                productList.add(product);
            }
            if (!productList.isEmpty()) {
                return productList;
            }
            throw new DaoRecordNotFoundException("Invalid productcategory");

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
