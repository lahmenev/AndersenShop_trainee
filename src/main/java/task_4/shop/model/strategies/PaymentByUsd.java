package task_4.shop.model.strategies;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class PaymentByUsd implements CurrencyStrategy {

    /**
     * Counts price for products
     *
     * @param price argument of price
     * @param amount argument of product's amount
     * @return
     */
    @Override
    public int payment(int price, int amount) {
        return price * amount * 65;
    }
}
