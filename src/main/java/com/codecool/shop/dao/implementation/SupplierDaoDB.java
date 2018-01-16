package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.db.ConnectionHandler;
import com.codecool.shop.model.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupplierDaoDB implements SupplierDao {

    private static SupplierDaoDB instance = null;
    private static final Logger logger = LoggerFactory.getLogger(SupplierDaoMem.class);

    private SupplierDaoDB() {
    }

    public static SupplierDaoDB getInstance() {
        if (instance == null) {
            instance = new SupplierDaoDB();
        }
        return instance;
    }

    @Override
    public void add(Supplier supplier) throws SQLException{

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "INSERT INTO suppliers (name, description) VALUES (?, ?);";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.execute();
            logger.debug("{} is added to the database", supplier);
        }

    }

    @Override
    public Supplier find(int id) throws SQLException {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM suppliers WHERE id =  ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description")
                );
                supplier.setId(id);
                logger.debug("Supplier {} found succesfully", supplier);
                return supplier;
            }
            else {
                logger.error("{} is invalid argument", id);
                throw new IllegalArgumentException("Invalid id");
            }
        }
    }

    @Override
    public void remove(int id) throws SQLException{

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "DELETE FROM suppliers WHERE id = ?";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();
            logger.debug("Supplier with id of {} deleted successfully from the database", id);
        }
    }

    @Override
    public List<Supplier> getAll() throws SQLException{

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT id FROM suppliers";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = statement.executeQuery();

            List<Supplier> allSuppliersList = new ArrayList<>();

            while (resultSet.next()) {
                Supplier supplier = this.find(resultSet.getInt("id"));

                logger.info("Supplier {} is added to allSuppliersList", supplier);
                allSuppliersList.add(supplier);
            }
            logger.debug("{} is the list of All Suppliers", allSuppliersList);
            return allSuppliersList;
        }
    }
}
