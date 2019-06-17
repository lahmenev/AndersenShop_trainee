package task_3.shop.model;

import task_3.shop.model.strategies.CurrencyStrategy;
import task_3.shop.model.strategies.PaymentByDefault;
import task_3.shop.model.strategies.PaymentByRub;
import task_3.shop.model.strategies.PaymentByUsd;
import task_3.shop.service.DBConnection;
import java.io.Serializable;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Bucket implements Serializable {
    private static final long serialVersionUID = 8578614558107783054L;
    private CurrencyStrategy strategy;
    private Map<String, CurrencyStrategy> rubMap = new HashMap<>();
    private Map<String, CurrencyStrategy> usdMap = new HashMap<>();

    /**
     * Displays bucket
     *
     * @param user input parameter of user
     */
    public void displayBucket(User user) {
        String name;
        String currency;
        int id;
        int price;
        int amount;
        int user_id = user.getId();
        int finalPrice = 0;

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from orders where user_id = '"+user_id+"'")) {

            while (rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
                price = rs.getInt("price");
                amount = rs.getInt("amount");
                currency = rs.getString("currency");

                finalPrice += paymentFinalPrice(currency, price, amount);
                System.out.printf("id = %d, name = %s, amount = %d\n", id, name, amount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Final price = " + finalPrice);
    }

    /**
     * Adds product into bucket
     *
     * @param id of product
     * @param user input parameter of user
     * @param product input product item
     */
    public void addToBucket(int id, User user, Product product) {
        PreparedStatement statement = null;
        String name = product.getName();
        String currency = product.getCurrency();
        int price = product.getPrice();
        int amount = product.getAmount();
        int user_id = user.getId();
        int sum = paymentFinalPrice(currency, price, amount);

        try (Connection connection = DBConnection.getConnection()) {
            statement = connection.prepareStatement("insert into orders" +
                    "(name, currency, price, amount, user_id, summ) values " +
                    "('"+name+"', '"+currency+"', '"+price+"', '"+amount+"', '"+user_id+"', '"+sum+"')");

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
     * @param user input parameter of user
     */
    public void clearBucket(User user) {
        int id = user.getId();
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
     * @param currency currency of product
     * @param price price for product
     * @param amount amount of product
     * @return
     */
    public int paymentFinalPrice(String currency, int price, int amount) {
        PaymentByRub rubPayment = new PaymentByRub();
        PaymentByUsd usdPayment = new PaymentByUsd();

        rubMap.put("RUB", rubPayment);
        usdMap.put("USD", usdPayment);

        if (currency.equals("RUB")) {
            strategy = rubMap.getOrDefault("RUB", new PaymentByDefault());
        } else if (currency.equals("USD")) {
            strategy = rubMap.getOrDefault("USD", new PaymentByDefault());
        }

        return strategy.payment(price, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return strategy.equals(bucket.strategy) &&
                rubMap.equals(bucket.rubMap) &&
                usdMap.equals(bucket.usdMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(strategy, rubMap, usdMap);
    }
}
