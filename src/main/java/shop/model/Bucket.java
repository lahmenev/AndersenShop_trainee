package shop.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Bucket {
    private Map<Integer, Product> bucket = new TreeMap<>();

    /**
     * Display products in bucket
     */
    public void displayBucket() {
        System.out.println("Products in bucket:");

        if (bucket.isEmpty()) {
            System.out.println("Bucket is empty");
        }

        for (Map.Entry<Integer, Product> productEntry : bucket.entrySet()) {
            System.out.println(productEntry.getValue());
        }
    }

    /**
     * Gets product from stock
     *
     * @param stock storage of product
     * @param id identifier of product
     * @return product from stack
     */
    private Product getProduct(Stock stock, int id) {
        return stock.getStock().get(id);
    }

    /**
     * Adds product into bucket
     *
     * @param stock storage of product
     * @param id identifier of product
     */
    public void addToBucket(Stock stock, int id) {

        for (Map.Entry<Integer, Product> buckets : bucket.entrySet()) {

            if (buckets.getKey().equals(id)) {
                System.out.printf("Product %s has already added\n", buckets.getValue().getName());
            }
        }

        bucket.put(id, getProduct(stock, id));
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

    public Map<Integer, Product> getBucket() {
        return bucket;
    }
}
