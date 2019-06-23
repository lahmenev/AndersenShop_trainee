package task_6.shop.service;

import task_6.shop.exception.ProductTransactionException;
import task_6.shop.model.Product;
import task_6.shop.model.User;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface StockService {

    /**
     * Gets list of products from database
     *
     * @return list of products
     */
    List<Product> getStockList();

    /**
     * Adds product to List
     *
     * @param product item of products
     */
    void addProduct(Product product);

    /**
     * Removes product
     *
     * @param id input parameter
     */
    void deleteProduct(int id);

    /**
     * Adds product to bucket
     *
     * @param product input parameter of Product
     * @param user input parameter of User
     */
    void addToBucket(Product product, User user) throws ProductTransactionException;
}
