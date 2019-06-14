package task_2.shop.model.strategies;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class PaymentByDefault implements CurrencyStrategy{

    /**
     * Counts price for products by default case
     *
     * @param price argument of price
     * @param amount argument of product's amount
     * @return value of final price
     */
    @Override
    public int payment(int price, int amount) {
        return price * amount;
    }
}
