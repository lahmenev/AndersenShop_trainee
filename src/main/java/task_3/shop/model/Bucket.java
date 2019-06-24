package task_3.shop.model;

import task_3.shop.model.strategies.CurrencyStrategy;
import task_3.shop.model.strategies.PaymentByRub;
import task_3.shop.model.strategies.PaymentByUsd;
import task_3.shop.service.DBConnection;
import java.io.Serializable;
import java.sql.*;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Bucket implements Serializable {
    private static final long serialVersionUID = 1L;
    private CurrencyStrategy strategy;

    /**
     * Displays bucket
     *
     * @param customer input parameter of user
     */
    public void displayBucket(Customer customer) {
        int id;
        String name;
        String currancy;
        int price;
        int amount;
        int user_id = customer.getId();
        int finalPrice = 0;

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from orders where user_id = '"+user_id+"'")) {

            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                price = rs.getInt("price");
                amount = rs.getInt("amount");
                currancy = rs.getString("currancy");

                finalPrice += paymentFinalPrice(currancy, price, amount);
                System.out.printf("id = %d, name = %s, amount = %d\n", id, name, amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Final prcie = " + finalPrice);
    }

    /**
     * Adds product into bucket
     *
     * @param id of product
     * @param customer input parameter of user
     * @param product input product item
     */
    public void addToBucket(int id, Customer customer, Product product) {
        String name = product.getName();
        String currancy = product.getCurrency();
        int price = product.getPrice();
        int amount = product.getAmount();
        int user_id = customer.getId();
        int summ = paymentFinalPrice(currancy, price, amount);

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into orders" +
                     "(name, currancy, price, amount, user_id, summ) values " +
                     "('"+name+"', '"+currancy+"', '"+price+"', '"+amount+"', '"+user_id+"', '"+summ+"')")) {

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes product from bucket
     *
     * @param id identifier of product
     */
    public void delFromBucket(int id) {

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {
            int rows = statement.executeUpdate("delete from orders where id = '" + id + "'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Clears bucket
     *
     * @param customer input parameter of user
     */
    public void clearBucket(Customer customer) {
        int id = customer.getId();
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement()) {

            int rows = statement.executeUpdate("delete from orders where user_id = '"+id+"'");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Counts final price for buying
     *
     * @param currancy currancy of product
     * @param price price for product
     * @param amount amount of product
     * @return
     */
    public int paymentFinalPrice(String currancy, int price, int amount) {

        if (currancy.equals("RUB")) {
            strategy = new PaymentByRub();
        } else if (currancy.equals("USD")) {
            strategy = new PaymentByUsd();
        }

        return strategy.payment(price, amount);
    }
}
