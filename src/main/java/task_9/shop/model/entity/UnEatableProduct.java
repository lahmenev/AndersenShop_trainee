package task_9.shop.model.entity;

import javax.persistence.Entity;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
@Entity
public class UnEatableProduct extends Product {

    public UnEatableProduct() {
    }

    public UnEatableProduct(Integer id) {
        super(id);
    }

    public UnEatableProduct(String name, String currency, int price, int amount, Country country) {
        super(name, currency, price, amount, country);
    }

    public UnEatableProduct(int id, String name, String currency, int price, int amount, Country country) {
        super(id, name, currency, price, amount, country);
    }
}
