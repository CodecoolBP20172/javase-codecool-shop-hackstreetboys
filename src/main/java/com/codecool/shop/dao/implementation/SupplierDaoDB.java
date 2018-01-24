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

/**
 * This is a SupplierDaoDB class, which implements SupplierDao Interface.
 * <p>The purpose of this class is to store Suppliers into database</p>
 */
public class SupplierDaoDB implements SupplierDao {

    private static SupplierDaoDB instance = null;
    private static final Logger logger = LoggerFactory.getLogger(SupplierDaoMem.class);

    /**
     A private Constructor prevents any other class from instantiating.
     */
    private SupplierDaoDB() {
    }

    /**
     Creates a Singleton instance.
     @return the same SupplierDaoDB instance everytime.
     */
    public static SupplierDaoDB getInstance() {
        if (instance == null) {
            instance = new SupplierDaoDB();
        }
        return instance;
    }

    /**
     Add Supplier object to the database.
     <p>If it gets SQLException throws it to the SupplierDao Interface.</p>
     @param supplier - is the supplier object, which we want to add to the database.
     */
    @Override
    public void add(Supplier supplier) throws SQLException{

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "INSERT INTO suppliers (name, description) VALUES (?, ?);";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.execute();
            logger.info("Supplier:( {} ) is added to the database", supplier);
        }

    }

    /**
     Find Supplier object by it's id in the database.
     <p>If it gets SQLException throws it to the SupplierDao Interface.</p>
     @param id - is the id of the Supplier object in the database.
     @return the Supplier object.
     @throws IllegalArgumentException if the id is invalid.
     */
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
                logger.info("Supplier {} found succesfully", supplier);
                return supplier;
            }
            else {
                logger.warn("{} is invalid argument", id);
                throw new IllegalArgumentException("Invalid id");
            }
        }
    }

    /**
     Remove Supplier object by it's id from the database.
     <p>If it gets SQLException throws it to the SupplierDao Interface.</p>
     @param id - is the id of the Supplier object in the database.
     */
    @Override
    public void remove(int id) throws SQLException{

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "DELETE FROM suppliers WHERE id = ?";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);
            statement.execute();
            logger.info("Supplier with id of {} deleted successfully from the database", id);
        }
    }

    /**
     Find Supplier objects in the database and put them into an ArrayList.
     <p>If it gets SQLException throws it to the SupplierDao Interface.</p>
     @return the ArrayList within Supplier objects.
     */
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
            logger.info("{} is the list of All Suppliers", allSuppliersList);
            return allSuppliersList;
        }
    }
}