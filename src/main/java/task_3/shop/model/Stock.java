package task_3.shop.model;

import task_3.shop.service.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Stock implements StockInterface {
    private List<Product> stockList = new ArrayList<>();

    /**
     * Creates product
     *
     * @param product is item with unique id
     */
    public void createProduct(Product product) {
        String name = product.getName();
        String currency = product.getCurrency();
        int price = product.getPrice();
        int amount = product.getAmount();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into products " +
                     "(name, currency, price, amount) value ('"+name+"', '"+currency+"', '"+price+"', '"+amount+"')")) {

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        addToList(product);
    }

    /**
     * Adds products from database to List.
     *
     * @param product input product for adding
     */
    private void addToList(Product product) {

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from products")) {

            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setCurrency(rs.getString("currency"));
                product.setPrice(rs.getInt("price"));
                product.setAmount(rs.getInt("amount"));
            }

            stockList.add(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays product in the stock
     */
    public void displayProducts() {
        for (Product product : stockList) {
            System.out.println(product);
        }
    }

    /**
     * Gets name of product by id
     *
     * @param id identify of product
     * @return name of product
     */
    public String getNameById(int id) {
        String name = null;

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select name from products where id = '"+id+"'")) {
            name = rs.getString("name");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    /**
     * Gets currency of product by id
     *
     * @param id identify of product
     * @return currancy string
     */
    public String getCurrencyNameById(int id) {
        String currency = null;

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select currancy from products where id = '"+id+"'")) {
            currency = rs.getString("currency");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return currency;
    }

    /**
     * Gets price of product by id
     *
     * @param id identify of product
     * @return price of product
     */
    public int getPriceById(int id) {
        int price = 0;

        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select price from products where id = '"+id+"'")) {
            price = rs.getInt("price");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return price;
    }

    /**
     * Gets stock
     *
     * @return stock
     */
    public List<Product> getStock() {
        return stockList;
    }
}
