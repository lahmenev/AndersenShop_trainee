package shop.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Stock {
    private Map<Integer, Product> stock = new TreeMap<>();

    /**
     * Creates product
     *
     * @param product is item with unique id
     */
    public void createProduct(Product product) {
        stock.put(product.getId(), product);
    }

    /**
     * Displays product in the stock
     */
    public void displayProducts() {
        System.out.println("Products in the stock:");
        for (Map.Entry<Integer, Product> productEntry : stock.entrySet()) {
            System.out.println(productEntry.getValue());
        }
    }

    public Map<Integer, Product> getStock() {
        return stock;
    }
}
