package task_3.shop.model;

import java.io.Serializable;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Product implements Serializable {
    private static final long serialVersionUID = -6837110816313088515L;
    private int id;
    private String name;
    private int price;
    private int amount;
    private static int counter;
    private String currency;

    public Product() {
    }

    public Product(String name, String currency, int price, int amount) {
        this.name = name;
        this.currency = currency;
        this.price = price;
        this.amount = amount;
        counter++;
        this.id = counter;
    }

    public void setActualAmountForBucket(int amount) {
        this.amount += amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id = " + id +
                ", name = '" + name + '\'' +
                ", price = " + price +
                ", amount = " + amount +
                ", currency = " + currency +
                '}';
    }
}
