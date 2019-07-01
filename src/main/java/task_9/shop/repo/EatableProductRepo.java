package task_9.shop.repo;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task_9.shop.model.entity.EatableProduct;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface EatableProductRepo extends ProductRepo<EatableProduct> {

    /**
     * Update eatableProduct item in database
     *
     * @param amount input parameter of amount
     * @param id input parameter of id
     */
    @Modifying
    @Query("update EatableProduct set amount = :amount where id = :id")
    void update(@Param("amount") int amount, @Param("id") int id);

    /**
     * Gets eatableProduct item from database
     *
     * @param id input parameter of eatableProduct item
     * @return eatableProduct item
     */
    EatableProduct getEatableProductById(int id);


}
