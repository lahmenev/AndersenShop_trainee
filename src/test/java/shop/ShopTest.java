package shop;

import org.junit.Before;
import org.junit.Test;
import shop.model.Bucket;
import shop.model.FoodProduct;
import shop.model.NonFoodProduct;
import shop.model.Stock;

import static org.junit.Assert.assertEquals;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class ShopTest {
    private Stock stock;
    private Bucket bucket;

    /**
     * Initialize method for testing
     */
    @Before
    public void init() {
        stock = new Stock();
        bucket = new Bucket();
        stock.createProduct(new NonFoodProduct("car", 10_000, 2));
        stock.createProduct(new NonFoodProduct("laptop", 1000, 5));
        stock.createProduct(new FoodProduct("molk", 50, 100));
    }

    /**
     * Adds one item to bucket and check amount of bucket's product and name of product
     */
    @Test
    public void testAddToBucket() {
        bucket.addToBucket(stock, 1);

        assertEquals(1, bucket.getBucket().size());
        assertEquals("car", bucket.getBucket().get(1).getName());
    }

    /**
     * Removes product from bucket and check amount of product
     */
    @Test
    public void testDelFromBucket() {
        bucket.addToBucket(stock, 1);
        bucket.delFromBucket(1);

        assertEquals(0, bucket.getBucket().size());
    }

    /**
     * Clears bucket and check size of bucket
     */
    @Test
    public void testClearBucket() {
        bucket.addToBucket(stock, 1);
        bucket.addToBucket(stock, 2);
        bucket.clearBucket();

        assertEquals(0, bucket.getBucket().size());
    }
}
