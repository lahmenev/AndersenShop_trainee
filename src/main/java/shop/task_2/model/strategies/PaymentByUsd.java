package shop.task_2.model.strategies;

import shop.task_2.model.Currency;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class PaymentByUsd implements CurrancyStrategy {
    private Currency usd = new Currency("Dollar USA", 65, "USD");

    /**
     * Counts price for products
     *
     * @param price argument of price
     * @param amount argument of product's amount
     * @return
     */
    @Override
    public int payment(int price, int amount) {
        return price * amount * usd.getCurrencyGain();
    }

    /**
     *
     * @return Currency object
     */
    public Currency getUsd() {
        return usd;
    }
}
