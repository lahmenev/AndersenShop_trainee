package task_7.shop.DAO;

import task_7.shop.model.BucketItem;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface BucketDAO {

    /**
     * Gets list of item
     *
     * @param userName input parameter of user
     * @return List of BucketItem objects
     */
    List<BucketItem> getAllItem(String userName);
}
