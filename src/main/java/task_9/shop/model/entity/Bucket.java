package task_9.shop.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
@Entity
@Table(name = "orders")
public class Bucket extends Product{
    private String shelfLife;
    private String customer;
    private int sum;

    public Bucket() {
    }

    public Bucket(EatableProduct product, int sum, String customer) {
        super(product.getId(), product.getName(), product.getCurrency(),
                product.getPrice(), product.getAmount(), product.getCountry());

        this.shelfLife = product.getShelfLife();
        this.sum = sum;
        this.customer = customer;
    }

    public Bucket(UnEatableProduct product, int sum, String customer) {
        super(product.getId(), product.getName(), product.getCurrency(),
                product.getPrice(), product.getAmount(), product.getCountry());

        this.sum = sum;
        this.customer = customer;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
    }
}
