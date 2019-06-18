package task_6.shop.model;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class BucketItem {
    private String name;
    private String currency;
    private int id;
    private int price;
    private int amount;
    private int sum;

    public BucketItem(String name, String currency, int price, int amount, int sum) {
        this.name = name;
        this.currency = currency;
        this.price = price;
        this.amount = amount;
        this.sum = sum;
    }

    public BucketItem(int id, String name, String currency, int price, int amount, int sum) {
        this(name, currency, price, amount, sum);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
