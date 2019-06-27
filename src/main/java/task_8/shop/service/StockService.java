package task_8.shop.service;

import task_8.shop.exception.ProductTransactionException;
import task_8.shop.model.entity.Product;
import task_8.shop.model.projections.ProductInfo;
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
     * @param userName input parameter of User
     * @throws ProductTransactionException
     */
    void addToBucket(Product product, String userName) throws ProductTransactionException;

    /**
     * Gets information about product
     *
     * @param id input parameter of id
     * @return ProductInfo instance
     */
    ProductInfo getProductInfo(int id);
}
