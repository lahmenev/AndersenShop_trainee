package task_7.shop.service;

import task_7.shop.DAO.BucketDAO;
import task_7.shop.DAO.implement.BucketDAOImpl;
import task_7.shop.model.BucketItem;
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
     * @param userName input parameter of user
     * @return List of BucketItem objects
     */
    public List<BucketItem> getBucketList(String userName) {
        BucketDAO bucketDAO = new BucketDAOImpl();
        return bucketDAO.getAllItem(userName);
    }
}
