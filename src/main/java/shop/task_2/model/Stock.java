package shop.task_2.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class Stock implements StockInterface {
    private Map<Integer, Product> stock = new TreeMap<>();
    private int size;

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

    /**
     * Removes product from stock
     *
     * @param id identify of product
     */
    public void delFromStock(int id) {
        stock.remove(id);
    }

    /**
     * Gets name of product by id
     *
     * @param id identify of product
     * @return name of product
     */
    public String getNameById(int id) {
       return stock.get(id).getName();
    }

    /**
     * Gets currency of product by id
     *
     * @param id identify of product
     * @return Currency object
     */
    public Currency getCurrencyById(int id) {
        return stock.get(id).getCurrency();
    }

    /**
     * Gets price of product by id
     *
     * @param id identify of product
     * @return price of product
     */
    public int getPriceById(int id) {
        return stock.get(id).getPrice();
    }

    /**
     * Gets size of stock
     *
     * @return size of stock
     */
    public int getSize() {
        return size = stock.size();
    }

    /**
     * Gets stock
     *
     * @return stock
     */
    public Map<Integer, Product> getStock() {
        return stock;
    }
}
