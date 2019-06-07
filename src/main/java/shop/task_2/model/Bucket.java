package shop.task_2.model;

import shop.task_2.model.strategies.CurrancyStrategy;
import shop.task_2.model.strategies.PaymentByRub;
import shop.task_2.model.strategies.PaymentByUsd;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Bucket implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<Integer, Product> bucket = new TreeMap<>();
    private CurrancyStrategy strategy;
    private int size;

    /**
     * Display products in bucket
     */
    public void displayBucket(int id) {
        System.out.println("Products in bucket:");

        if (bucket.isEmpty()) {
            System.out.println("Bucket is empty");
        }

        for (Map.Entry<Integer, Product> productEntry : bucket.entrySet()) {
            System.out.printf("Product: %s, amount of product: %d\n" , productEntry.getValue().getName(),
                    productEntry.getValue().getAmount());
        }

    }

    public void addToBucket(Integer id, Product product) {
        if (bucket.get(id) == null) {
            bucket.put(id, product);
        } else {
            bucket.get(id).setActualAmountForBucket(product.getAmount());
        }
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

        if (product.getCurrency().getCurrencyCode().equals("RUB")) {
            strategy = new PaymentByRub();
        } else if (product.getCurrency().getCurrencyCode().equals("USD")) {
            strategy = new PaymentByUsd();
        }

        return strategy.payment(product.getPrice(), product.getAmount());
    }

    public Map<Integer, Product> getBucket() {
        return bucket;
    }

    public int getSize() {
        return size = bucket.size();
    }
}
