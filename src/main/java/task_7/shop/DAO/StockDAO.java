package task_7.shop.DAO;

import task_7.shop.exception.ProductTransactionException;
import task_7.shop.model.BucketItem;
import task_7.shop.model.Product;

import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface StockDAO {

    /**
     * Gets list of items
     *
     * @return list of products
     */
    List<Product> getAllItem();

    /**
     * Insert item
     *
     * @param product item of products
     */
    void insertItem(Product product);

    /**
     * Removes item
     *
     * @param id input parameter
     */
    void deleteItem(int id);

    /**
     * Insert item to bucket
     *
     * @param product input parameter of Product
     * @param userName input parameter of User
     * @throws ProductTransactionException
     */
    void insertToBucket(Product product, String userName) throws ProductTransactionException;

    /**
     * Gets item by id
     *
     * @param id input parameter of identity
     * @return BucretItem object
     */
    BucketItem getItemById(int id);
}
