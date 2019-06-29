package task_9.shop.model.entity;

import javax.persistence.Entity;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
@Entity
public class EatableProduct extends Product{
    private String shelfLife;

    public EatableProduct() {
    }

    public EatableProduct(int id, String name, String currency, int price, int amount, Country country, String shelfLife) {
        super(id, name, currency, price, amount, country);
        this.shelfLife = shelfLife;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }
}
