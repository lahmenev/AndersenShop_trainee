package task_9.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import task_9.shop.model.entity.Product;
import task_9.shop.model.projections.ProductInfo;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface ProductRepo<E extends Product> extends JpaRepository<E, Integer> {

    /**
     * Select item from database
     *
     * @return list of item
     */
    @Override
    List<E> findAll();

    /**
     * Insert item into database
     *
     * @param s input parameter of item object
     * @param <S> Type of item
     * @return Item object instance
     */
    @Override
    <S extends E> S save(S s);

    /**
     * Select information about item from database
     *
     * Select item from database
     * @param id input parameter of item's identify
     * @return ProductInfo instance
     */
    ProductInfo findProductById(int id);

    /**
     * Removes item from database by id
     *
     * @param id input parameter of item's identify
     */
    @Override
    void deleteById(Integer id);
}
