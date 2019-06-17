package task_4.shop.service;

import task_4.shop.DAO.StockDAO;
import task_4.shop.model.BucketItem;
import task_4.shop.model.Product;
import task_4.shop.model.strategies.CurrencyStrategy;
import task_4.shop.model.strategies.PaymentByRub;
import task_4.shop.model.strategies.PaymentByUsd;
import task_4.shop.utils.DBUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class StockService implements StockDAO {
    private static CurrencyStrategy strategy;

    /**
     * Gets list of products from database
     *
     * @return list of products
     */
    @Override
    public List<Product> getAllProducts() {
        List<Product> listOfProduct = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from products")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String currancy = rs.getString("currancy");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                Product product = new Product(id, name, currancy, price, amount);
                listOfProduct.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfProduct;
    }

    /**
     * Adds product to List
     *
     * @param product item of products
     */
    @Override
    public void addProduct(Product product) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into products (name, currancy, price, amount)" +
                     "values (?, ?, ?, ?)")){

            statement.setString(1, product.getName());
            statement.setString(2, product.getCurrancy());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getAmount());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes product from List
     *
     * @param product item of products
     */
    @Override
    public void deleteProduct(Product product) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("delete from products where id = ?")) {

            statement.setInt(1, product.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds product to bucket
     *
     * @param product item of products
     */
    @Override
    public void addToBucket(Product product) {
        int productId = product.getId();
        BucketItem bucketItem = getProductById(productId);

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into orders (name, currancy, price, amount, summ) " +
                     "values (?, ?, ?, ?, ?)")) {

            statement.setString(1, bucketItem.getName());
            statement.setString(2, bucketItem.getCurrancy());
            statement.setInt(3, bucketItem.getPrice());
            statement.setInt(4, bucketItem.getAmount());
            statement.setInt(5, bucketItem.getSum());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets product by id
     *
     * @param id identify of product
     * @return object of BucketItem
     */
    @Override
    public BucketItem getProductById(int id) {
        BucketItem item = null;

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from products where id = '"+id+"'")) {

            if (rs.next()) {
                String name = rs.getString("name");
                String currancy = rs.getString("currancy");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                int sum = paymentFinalPrice(currancy, price, amount);
                item = new BucketItem(name, currancy, price, amount, sum);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return item;
    }

    /**
     * Counts final price for buying
     *
     * @param currancy currancy of product
     * @param price price for product
     * @param amount amount of product
     * @return integer value of sum
     */
    private int paymentFinalPrice(String currancy, int price, int amount) {

        if (currancy.equals("RUB")) {
            strategy = new PaymentByRub();
        } else if (currancy.equals("USD")) {
            strategy = new PaymentByUsd();
        }

        return strategy.payment(price, amount);
    }
}
