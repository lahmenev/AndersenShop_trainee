package task_9.shop.model.projections;

import task_9.shop.model.entity.Country;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface ProductInfo {

    /**
     * Gets name of Product
     *
     * @return product's name
     */
    String getName();

    /**
     * Gets Country object of Product
     *
     * @return instance of Country object
     */
    Country getCountry();

    /**
     * Gets price of Product
     *
     * @return price
     */
    int getPrice();

    /**
     * Gets currency of Product
     *
     * @return currency
     */
    String getCurrency();
}
