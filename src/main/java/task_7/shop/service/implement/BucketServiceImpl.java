package task_7.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import task_7.shop.DAO.BucketDAO;
import task_7.shop.model.BucketItem;
import task_7.shop.service.BucketService;

import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class BucketServiceImpl implements BucketService {

    @Autowired
    BucketDAO bucketDAO;

    /**
     * Gets list of bucket item
     *
     * @param userName input parameter of user
     * @return List of BucketItem objects
     */
    @Override
    public List<BucketItem> getBucketList(String userName) {
        return bucketDAO.getAllItem(userName);
    }
}
