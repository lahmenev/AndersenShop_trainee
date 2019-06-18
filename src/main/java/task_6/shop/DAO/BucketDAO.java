package task_6.shop.DAO;

import task_6.shop.model.BucketItem;
import task_6.shop.model.User;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface BucketDAO {

    /**
     * Gets list of bucket from database
     *
     * @param user input parameter of user
     * @return List of BucketItem objects
     */
    List<BucketItem> getBucketList(User user);
}
