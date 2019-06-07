package task_2.shop.model.strategies;

import task_2.shop.model.Currency;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class PaymentByRub implements CurrancyStrategy {
    private Currency rub = new Currency("Russian ruble", 1, "RUB");

    /**
     * Counts price for products
     *
     * @param price argument of price
     * @param amount argument of product's amount
     * @return final price for products in rubles
     */
    @Override
    public int payment(int price, int amount) {
        return price * amount * rub.getCurrencyGain();
    }

    /**
     *
     * @return Currency object
     */
    public Currency getRub() {
        return rub;
    }
}
