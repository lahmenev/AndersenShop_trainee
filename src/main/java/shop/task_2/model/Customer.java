package shop.task_2.model;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Customer {
    private String name;
    private int money;
    private Bucket bucket;

    private int idOfProductForBucket;
    private int amountofProductForBucket;

    public Customer(String name, int money) {
        this.name = name;
        this.money = money;
        this.bucket = new Bucket();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

    public int getIdOfProductForBucket() {
        return idOfProductForBucket;
    }

    public void setIdOfProductForBucket(int idOfProductForBucket) {
        this.idOfProductForBucket = idOfProductForBucket;
    }

    public int getAmountofProductForBucket() {
        return amountofProductForBucket;
    }

    public void setAmountofProductForBucket(int amountofProductForBucket) {
        this.amountofProductForBucket = amountofProductForBucket;
    }
}
