package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.db.ConnectionHandler;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class SupplierDaoDB implements SupplierDao {

    private static SupplierDaoDB instance = null;

    private SupplierDaoDB() {
    }

    public static SupplierDaoDB getInstance() {
        if (instance == null) {
            instance = new SupplierDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "INSERT INTO supplier (id, name, description) VALUES (?, ?, ?);";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, supplier.getId());
            statement.setString(2, supplier.getName());
            statement.setString(3, supplier.getDescription());
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid Supplier or null", e);
        }
    }

    @Override
    public Supplier find(int id) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM supplier WHERE id =  ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery(sqlStatement);
            if (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                return supplier;
            }
            throw new IllegalArgumentException("Invalid id");

        } catch (SQLException e) {
            throw new IllegalArgumentException("Connection is not working");
        }
    }

    @Override
    public void remove(int id) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "DELETE FROM suppliers WHERE id = ?";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid Supplier or null", e);
        }
    }

    @Override
    public List<Supplier> getAll() {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM suppliers";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery(sqlStatement);

            List<Supplier> resultList = new ArrayList<>();

            while (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                resultList.add(supplier);
            }
            return resultList;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid id");
        }
    }
}
