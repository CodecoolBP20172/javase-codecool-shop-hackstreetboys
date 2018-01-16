package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.db.ConnectionHandler;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.AutoCloseable;
import java.util.Map;

public class OrderDaoDB implements OrderDao {

    private static final OrderDaoDB instance = new OrderDaoDB();

    private OrderDaoDB() {
    }

    public static OrderDaoDB getInstance() {
        return instance;
    }

    public Order getOrderForUser(Integer userId) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM orders WHERE user_id =  ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer user_id = resultSet.getInt("user_id");
                Order order = new Order(user_id);
                return order;
            }
            throw new IllegalArgumentException("Invalid id");

        } catch (SQLException e) {
            throw new IllegalArgumentException("Connection is not working", e);
        }
    }

    @Override
    public void add(Order order) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "INSERT INTO orders (user_id, numberOfProducts, totalPrice) VALUES (?, ?, ?);";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getNumberOfProducts());
            statement.setFloat(3, order.getTotalPrice());
            statement.execute();

            for(Map.Entry<Product, Integer> entry : order.getAll().entrySet()) {
                Integer product_id = entry.getKey().getId();
                Integer quantity = entry.getValue();

                String sqlStatement2 = "INSERT INTO ordered_products (product_id, quantity, order_id) VALUES (?, ?, ?);";
                PreparedStatement statement2 = connectionHandler.getConnection().prepareStatement(sqlStatement2);

                statement2.setInt(1, product_id);
                statement2.setInt(2, quantity);
                statement2.setInt(3, order.getId());
                statement2.execute();
            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid order or null", e);
        }
    }


    @Override
    public Order find(int id) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM orders WHERE user_id =  ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Order order= new Order (
                        resultSet.getInt("user_id")
                );

                String sqlStatement2 = "SELECT * FROM ordered_products WHERE order_id = ?";
                PreparedStatement statement2 = connectionHandler.getConnection().prepareStatement(sqlStatement2);
                statement2.setInt(1, order.getId());
                ResultSet resultSet2 = statement2.executeQuery();
                while (resultSet2.next()) {
                    Integer product_id = resultSet2.getInt("product_id");
                    Integer quantity = resultSet2.getInt("quantity");
                    Product product = ProductDaoDB.getInstance().find(product_id);
                    order.getAll().put(product, quantity);

                }

                return order;
            }
            throw new IllegalArgumentException("Invalid id");

        } catch (SQLException e) {
            throw new IllegalArgumentException("Connection is not working", e);
        }
    }

    @Override
    public void remove(int id) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement("DELETE FROM orders WHERE user_id = ?");
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid order or null", e);
        }
    }

    @Override
    public List<Order> getAll() {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement("SELECT user_id FROM orders");
            ResultSet resultSet = statement.executeQuery();

            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {

                orders.add(this.find(resultSet.getInt("user_id")));
            }
            return orders;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid order or null", e);
        }
    }
}
