package task_9.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import task_9.shop.model.entity.Bucket;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface BucketRepo extends JpaRepository<Bucket, Integer> {

    /**
     * Insert item into Database
     *
     * @param bucket input parameter
     * @return Bucket instance
     */
    @Override
    Bucket save(Bucket bucket);

    /**
     * Gets list of item
     *
     * @param userName input parameter of user
     * @return List of Bucket objects
     */
    default List<Bucket> getAllItem(String userName, EntityManagerFactory em) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Bucket> query = criteriaBuilder.createQuery(Bucket.class);
        Root<Bucket> bucketRoot = query.from(Bucket.class);

        query.select(bucketRoot).where(criteriaBuilder.equal(bucketRoot.get("customer"), userName));

        TypedQuery<Bucket> bucketTypedQuery = em.createEntityManager().createQuery(query);
        return bucketTypedQuery.getResultList();
    }
}
