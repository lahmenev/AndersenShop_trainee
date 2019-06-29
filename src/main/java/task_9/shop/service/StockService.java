package task_9.shop.service;

import task_9.shop.exception.ProductTransactionException;
import task_9.shop.model.entity.EatableProduct;
import task_9.shop.model.entity.UnEatableProduct;
import task_9.shop.model.projections.ProductInfo;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface StockService {

    /**
     * Gets list of eatable products from database
     *
     * @return list of products
     */
    List<EatableProduct> getEatableProductList();

    /**
     * Gets list of uneatable products from database
     *
     * @return list of products
     */
    List<UnEatableProduct> getUnEatableProductList();

    /**
     * Adds eatable product to List
     *
     * @param product item of products
     */
    void addEatableProduct(EatableProduct product);

    /**
     * Adds uneatable product to List
     *
     * @param product item of products
     */
    void addUnEatableProduct(UnEatableProduct product);

    /**
     * Removes eatable product
     *
     * @param id input parameter
     */
    void deleteEatableProduct(int id);

    /**
     * Removes uneatable product
     *
     * @param id input parameter
     */
    void deleteUnEatableProduct(int id);

    /**
     * Adds uneatable product to bucket
     *
     * @param product input parameter of Product
     * @param userName input parameter of User
     * @throws ProductTransactionException
     */
    void addToBucketUnEatableProduct(UnEatableProduct product, String userName) throws ProductTransactionException;

    /**
     * Adds eatable product to bucket
     *
     * @param product input parameter of Product
     * @param userName input parameter of User
     * @throws ProductTransactionException
     */
    void addToBucketEatableProduct(EatableProduct product, String userName) throws ProductTransactionException;

    /**
     * Gets information about eatable product
     *
     * @param id input parameter of id
     * @return ProductInfo instance
     */
    ProductInfo getEatableProductInfo(int id);

    /**
     * Gets information about uneatable product
     *
     * @param id input parameter of id
     * @return ProductInfo instance
     */
    ProductInfo getUnEatableProductInfo(int id);
}
