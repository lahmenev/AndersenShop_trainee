package task_9.shop.model.strategies;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface CurrencyStrategy {

    /**
     * Counts price for products
     *
     * @param price argument of price
     * @param amount argument of product's amount
     * @return value of final price
     */
    int payment(int price, int amount);
}
