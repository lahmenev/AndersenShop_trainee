package task_3.shop.model;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Customer {
    private int id;
    private String name;
    private int money;
    private static int counter;
    private Bucket bucket;

    private int idOfProductForBucket;
    private int amountOfProductForBucket;

    public Customer(String name, int money) {
        this.name = name;
        this.money = money;
        this.bucket = new Bucket();
        counter++;
        this.id = counter;
    }

    public int getId() {
        return id;
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

    public int getAmountOfProductForBucket() {
        return amountOfProductForBucket;
    }

    public void setAmountOfProductForBucket(int amountOfProductForBucket) {
        this.amountOfProductForBucket = amountOfProductForBucket;
    }
}
