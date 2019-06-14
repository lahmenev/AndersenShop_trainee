package task_2.shop.model;

import task_2.shop.model.strategies.CurrencyStrategy;
import task_2.shop.model.strategies.PaymentByDefault;
import task_2.shop.model.strategies.PaymentByRub;
import task_2.shop.model.strategies.PaymentByUsd;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Bucket implements Serializable {
    private static final long serialVersionUID = -7465087567897485789L;
    private Map<Integer, Product> bucket = new TreeMap<>();
    private Map<Currency, CurrencyStrategy> rubMap = new HashMap<>();
    private Map<Currency, CurrencyStrategy> usdMap = new HashMap<>();
    private CurrencyStrategy strategy;
    private int size;

    /**
     * Display products in bucket
     */
    public void displayBucket(int id) {
        System.out.println("Products in bucket:");

        if (bucket.isEmpty()) {
            System.out.println("Bucket is empty");
        }

        bucket.forEach((key, value) ->
                System.out.printf("Product: %s, amount of product: %d\n" , value.getName(), value.getAmount()));
    }

    public void addToBucket(int id, Product product) {
            bucket.putIfAbsent(id, product);
            bucket.get(id).setActualAmountForBucket(product.getAmount());
    }

    public Product getProductById(int id) {
       return bucket.get(id);
    }

    /**
     * Removes product from bucket
     *
     * @param id identifier of product
     */
    public void delFromBucket(int id) {

        if (bucket.get(id) == null) {
            System.out.printf("There is no product with id %d\n", id);
            return;
        }

        bucket.remove(id);
    }

    /**
     * Clears bucket
     */
    public void clearBucket() {
        bucket.clear();
    }

    public int paymentFinalPrice(Product product) {

        PaymentByRub rubPayment = new PaymentByRub();
        PaymentByUsd usdPayment = new PaymentByUsd();

        rubMap.put(rubPayment.getRub(), rubPayment);
        usdMap.put(usdPayment.getUsd(), usdPayment);

        if (product.getCurrency().getCurrencyCode().equals(rubPayment.getRub().getCurrencyCode())) {
            strategy = rubMap.getOrDefault(rubPayment.getRub(), new PaymentByDefault());
        } else if (product.getCurrency().getCurrencyCode().equals(usdPayment.getUsd().getCurrencyCode())) {
            strategy = usdMap.getOrDefault(usdPayment.getUsd(), new PaymentByDefault());
        }

        return strategy.payment(product.getPrice(), product.getAmount());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bucket bucket = (Bucket) o;
        return rubMap.equals(bucket.rubMap) &&
                usdMap.equals(bucket.usdMap) &&
                strategy.equals(bucket.strategy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rubMap, usdMap, strategy);
    }

    public Map<Integer, Product> getBucket() {
        return bucket;
    }

    public int getSize() {
        return size = bucket.size();
    }
}
