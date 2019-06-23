package task_6.shop.DAO.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import task_6.shop.DAO.StockDAO;
import task_6.shop.exception.ProductTransactionException;
import task_6.shop.model.BucketItem;
import task_6.shop.model.Product;
import task_6.shop.model.User;
import task_6.shop.model.strategies.CurrencyStrategy;
import task_6.shop.model.strategies.PaymentByDefault;
import task_6.shop.model.strategies.PaymentByRub;
import task_6.shop.model.strategies.PaymentByUsd;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class StockDAOImpl implements StockDAO {
    private static CurrencyStrategy strategy;

    @Autowired
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Gets list of items
     *
     * @return list of products
     */
    @Override
    public List<Product> getAllItem() {
        String sqlQuery = "select * from products";

        RowMapper<Product> productRowMapper = (rs, productItem) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String currency = rs.getString("currency");
            int price = rs.getInt("price");
            int amount = rs.getInt("amount");

            return new Product(id, name, currency, price, amount);
        };

        return template.query(sqlQuery, productRowMapper);
    }

    /**
     * Insert item
     *
     * @param product item of products
     */
    @Override
    public void insertItem(Product product) {
        String sqlQuery = "insert into products (name, currency, price, amount) values (?, ?, ?, ?)";

        String name = product.getName();
        String currency = product.getCurrency();
        int price = product.getPrice();
        int amount = product.getAmount();

        template.update(sqlQuery, name, currency, price, amount);
    }

    /**
     * Removes item
     *
     * @param id input parameter
     */
    @Override
    public void deleteItem(int id) {
        String sqlQuery = "delete from products where id = ?";
        template.update(sqlQuery, id);
    }

    /**
     * Insert item to bucket
     *
     * @param product input parameter of Product
     * @param user input parameter of User
     * @throws ProductTransactionException
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = ProductTransactionException.class)
    public void insertToBucket(Product product, User user) throws ProductTransactionException {
        String sqlQuery = "insert into orders (name, currency, price, amount, summ, userName) values (?, ?, ?, ?, ?, ?)";

        String name = product.getName();
        String currency = product.getCurrency();
        int id = product.getId();
        int price = product.getPrice();
        int amount = product.getAmount();
        int sum = paymentFinalPrice(currency, price, amount);
        int productAmount = getItemById(id).getAmount();
        int currentProductAmount = productAmount - amount;

        template.update(sqlQuery, name, currency, price, amount, sum, user.getName());
        updateProductAmount(id, currentProductAmount, productAmount);
    }

    /**
     * Gets item by id
     *
     * @param id input parameter of identity
     * @return BucretItem object
     */
    @Override
    public BucketItem getItemById(int id) {
        String sqlQuery = "select * from products where id = '"+ id +"'";

        RowMapper<BucketItem> bucketItemRowMapper = (rs, bucketItem) -> {
            String name = rs.getString("name");
            String currency = rs.getString("currency");
            int price = rs.getInt("price");
            int amount = rs.getInt("amount");
            int sum = paymentFinalPrice(currency, price, amount);

            Product product = new Product(name, currency, price, amount);
            return new BucketItem(product, sum);
        };

        return template.queryForObject(sqlQuery, bucketItemRowMapper);
    }

    /**
     * Counts final price for buying
     *
     * @param currency currency of product
     * @param price price for product
     * @param amount amount of product
     * @return integer value of sum
     */
    private int paymentFinalPrice(String currency, int price, int amount) {
        Map<String, CurrencyStrategy> rubMap = new HashMap<>();
        Map<String, CurrencyStrategy> usdMap = new HashMap<>();
        PaymentByRub rubPayment = new PaymentByRub();
        PaymentByUsd usdPayment = new PaymentByUsd();

        rubMap.put("RUB", rubPayment);
        usdMap.put("USD", usdPayment);

        if (currency.equals("RUB")) {
            strategy = rubMap.getOrDefault("RUB", new PaymentByDefault());
        } else if (currency.equals("USD")) {
            strategy = usdMap.getOrDefault("USD", new PaymentByDefault());
        }

        return strategy.payment(price, amount);
    }

    /**
     * Updates amount value of product
     *
     * @param id input parameter of identity
     * @param currentProductAmount input parameter of current amount
     * @param productAmount input parameter of amount
     * @throws ProductTransactionException
     */
    @Transactional(propagation = Propagation.MANDATORY)
    public void updateProductAmount(int id, int currentProductAmount, int productAmount) throws ProductTransactionException {

        if (currentProductAmount < 0) {
            throw new ProductTransactionException("There is no such amount of product: current amount : " + productAmount);
        }

        String sqlString = "update products set amount = ? where id = ?";
        template.update(sqlString, currentProductAmount, id);
    }
}
