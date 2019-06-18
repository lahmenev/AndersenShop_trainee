package task_3.shop.model;

import task_3.shop.controller.proxy.ProductValid;
import java.time.LocalDate;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class FoodProduct extends Product {
    private String currency;
    private String name;
    private int id;
    private int price;
    private int amount;

    @ProductValid
    private LocalDate validUntil;

    public FoodProduct(String name, String currency, int price, int amount) {
        super(name, currency, price, amount);
        this.name = name;
        this.currency = currency;
        this.price = price;
        this.amount = amount;
        this.id = super.getId();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", price = " + price +
                ", amount = " + amount +
                ", currency = " + currency + ", годен до " + validUntil +
                '}';
    }
}