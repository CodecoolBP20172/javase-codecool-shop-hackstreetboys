package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.db.ConnectionHandler;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;

import java.sql.*;
import java.util.*;
import java.lang.AutoCloseable;

public class OrderDaoDB implements OrderDao {

    private static final OrderDaoDB instance = new OrderDaoDB();

    private OrderDaoDB() {
    }

    public static OrderDaoDB getInstance() {
        return instance;
    }

    @Override
    public Order getOrderForUser(Integer userId) {
        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            String sqlStatement = "SELECT * FROM orders WHERE user_id =  ?";
            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer user_id = resultSet.getInt("user_id");
                Order order = new Order(user_id);
                order.setId(resultSet.getInt("id"));
                return order;
            }
            throw new IllegalArgumentException("Invalid id");

        } catch (SQLException e) {
            throw new IllegalArgumentException("Connection is not working", e);
        }
    }

    @Override
    public void add(Order order) {

        remove(order.getId());
        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            for (Map.Entry<Product, Integer> entry : order.getAll().entrySet()) {
                Integer product_id = entry.getKey().getId();
                Integer quantity = entry.getValue();

                for (int i = 0; i < quantity; i++) {
                    String sqlStatement = "INSERT INTO ordered_products (product_id, order_id) VALUES (?, ?);";
                    PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);

                    System.out.println(product_id);
                    statement.setInt(1, product_id);
                    statement.setInt(2, order.getId());
                    statement.execute();
                }

            }

        } catch (SQLException e) {
            throw new IllegalArgumentException("Invalid order or null", e);
        }
    }




    @Override
    public Order find(int userId) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            Order order = getOrderForUser(userId);
            Map<Product, Integer> products = new HashMap<>();

            String sqlStatement = "SELECT * FROM ordered_products WHERE order_id = ?";

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement(sqlStatement);
            statement.setInt(1, order.getId());
            ResultSet resultSet = statement.executeQuery();


            while (resultSet.next()) {

                Product product = ProductDaoDB.getInstance().find(resultSet.getInt("product_id"));
                order.add(product);

                return order;
            }
            return order;

        } catch (SQLException e) {
            throw new IllegalArgumentException("Connection is not working", e);
        }
    }

    @Override
    public void remove(int orderId) {

        try (ConnectionHandler connectionHandler = new ConnectionHandler()) {

            PreparedStatement statement = connectionHandler.getConnection().prepareStatement("DELETE FROM ordered_products WHERE order_id = ?");
            statement.setInt(1, orderId);
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
