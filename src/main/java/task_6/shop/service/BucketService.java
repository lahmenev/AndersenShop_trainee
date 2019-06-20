package task_6.shop.service;

import task_6.shop.DAO.BucketDAO;
import task_6.shop.DAO.implement.BucketDAOImpl;
import task_6.shop.model.BucketItem;
import task_6.shop.model.User;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class BucketService {

    /**
     * Gets list of bucket item
     *
     * @param user input parameter of user
     * @return List of BucketItem objects
     */
    public List<BucketItem> getBucketList(User user) {
        BucketDAO bucketDAO = new BucketDAOImpl();
        return bucketDAO.getAllItem(user);
    }
}
