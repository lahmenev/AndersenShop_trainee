package task_9.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import task_9.shop.model.entity.Bucket;
import task_9.shop.repo.BucketRepo;
import task_9.shop.service.BucketService;
import javax.persistence.EntityManagerFactory;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class BucketServiceImpl implements BucketService {

    @Autowired
    EntityManagerFactory em;

    @Autowired
    BucketRepo bucketRepo;

    /**
     * Gets list of bucket item
     *
     * @param userName input parameter of user
     * @return List of Bucket objects
     */
    @Override
    public List<Bucket> getBucketList(String userName) {
        return bucketRepo.getAllItem(userName, em);
    }
}
