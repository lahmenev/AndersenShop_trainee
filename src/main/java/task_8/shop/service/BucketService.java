package task_8.shop.service;

import task_8.shop.model.entity.Bucket;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface BucketService {

    /**
     * Gets list of bucket item
     *
     * @param userName input parameter of user
     * @return List of Bucket objects
     */
    List<Bucket> getBucketList(String userName);
}
