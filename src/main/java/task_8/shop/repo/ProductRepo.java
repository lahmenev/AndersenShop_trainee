package task_8.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import task_8.shop.model.entity.Product;
import task_8.shop.model.projections.ProductInfo;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface ProductRepo extends JpaRepository<Product, Integer> {

    /**
     * Select item from database
     *
     * @return List of item
     */
    @Override
    @Query("select p from Product p")
    List<Product> findAll();

    /**
     * Insert item into database
     *
     * @param product input parameter
     * @return Product instance
     */
    @Override
    Product save(Product product);

    /**
     * Removes item from database
     *
     * @param id input parameter
     */
    @Modifying
    @Query("delete from Product p where p.id = :id")
    void delete(@Param("id") int id);

    /**
     * Gets item by id
     *
     * @param id input parameter
     * @return Product instance
     */
    Product getProductsById(int id);

    /**
     * Update item in database
     *
     * @param amount input parameter of amount
     * @param id input parameter of id
     */
    @Modifying
    @Query("update Product set amount = :amount where id = :id")
    void update(@Param("amount") int amount, @Param("id") int id);

    /**
     * Select item from database
     *
     * @param id input parameter
     * @return ProductInfo instance
     */
    ProductInfo findProductById(int id);
}
