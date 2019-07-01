package task_9.shop.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task_9.shop.model.entity.UnEatableProduct;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface UnEatableProductRepo extends ProductRepo<UnEatableProduct> {

    /**
     * Update uneatable item in database
     *
     * @param amount input parameter of amount
     * @param id input parameter of id
     */
    @Modifying
    @Query("update UnEatableProduct set amount = :amount where id = :id")
    void update(@Param("amount") int amount, @Param("id") int id);

    /**
     * Gets uneatableProduct item from database
     *
     * @param id input parameter of uneatableProduct item
     * @return uneatableProduct item
     */
    UnEatableProduct getUnEatableProductById(int id);
}
