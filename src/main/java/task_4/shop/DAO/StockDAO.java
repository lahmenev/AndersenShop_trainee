package task_4.shop.DAO;

import task_4.shop.model.BucketItem;
import task_4.shop.model.Product;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface StockDAO {
    /**
     * Gets list of products from database
     *
     * @return list of products
     */
    List<Product> getAllProducts();

    /**
     * Adds product to List
     *
     * @param product item of products
     */
    void addProduct(Product product);

    /**
     * Removes product from List
     *
     * @param product item of products
     */
    void deleteProduct(Product product);

    /**
     * Adds product to bucket
     *
     * @param product item of products
     */
    void addToBucket(Product product);

    /**
     * Gets product by id
     *
     * @param id identify of product
     * @return object of BucketItem
     */
    BucketItem getProductById(int id);
}
