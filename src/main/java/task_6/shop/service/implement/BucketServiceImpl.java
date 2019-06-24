package task_6.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import task_6.shop.DAO.BucketDAO;
import task_6.shop.DAO.implement.BucketDAOImpl;
import task_6.shop.model.BucketItem;
import task_6.shop.model.User;
import task_6.shop.service.BucketService;

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

    public void setBucketDAO(BucketDAO bucketDAO) {
        this.bucketDAO = bucketDAO;
    }

    @Override
    public List<BucketItem> getBucketList(User user)  {
        return bucketDAO.getAllItem(user);
    }
}
