package task_4.shop.DAO;

import task_4.shop.model.BucketItem;
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
     * @return List of Bucket
     */
    List<BucketItem> getBucketList();

}
