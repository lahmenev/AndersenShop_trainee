package task_6.shop.service;

import task_6.shop.model.BucketItem;
import task_6.shop.model.User;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface BucketService {

    List<BucketItem> getBucketList(User user);
}
