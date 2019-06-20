package task_6.shop.DAO;

import task_6.shop.exception.ProductTransactionException;
import task_6.shop.model.BucketItem;
import task_6.shop.model.Product;
import task_6.shop.model.User;
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
     * @param user input parameter of User
     * @throws ProductTransactionException
     */
    void insertToBucket(Product product, User user) throws ProductTransactionException;

    /**
     * Gets item by id
     *
     * @param id input parameter of identity
     * @return BucretItem object
     */
    BucketItem getItemById(int id);
}
