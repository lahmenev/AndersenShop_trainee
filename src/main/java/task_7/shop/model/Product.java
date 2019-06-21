package task_7.shop.model;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Product {
    private String name;
    private String currency;
    private int id;
    private int price;
    private int amount;

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(String name, String currency, int price, int amount) {
        this.name = name;
        this.currency = currency;
        this.price = price;
        this.amount = amount;
    }

    public Product(int id, String name, String currency, int price, int amount) {
        this(name, currency, price, amount);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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
}
