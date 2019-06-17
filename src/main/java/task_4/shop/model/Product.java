package task_4.shop.model;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Product {
    private String name;
    private String currancy;
    private int id;
    private int price;
    private int amount;

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(String name, String currancy, int price, int amount) {
        this.name = name;
        this.currancy = currancy;
        this.price = price;
        this.amount = amount;
    }

    public Product(int id, String name, String currancy, int price, int amount) {
        this(name, currancy, price, amount);
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

    public String getCurrancy() {
        return currancy;
    }

    public void setCurrancy(String currancy) {
        this.currancy = currancy;
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
