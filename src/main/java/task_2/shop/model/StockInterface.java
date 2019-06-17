package task_2.shop.model;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface StockInterface {

    /**
     * Creates product
     *
     * @param product is item with unique id
     */
    void createProduct(Product product);

    /**
     * Displays product in the stock
     */
    void displayProducts();

    /**
     * Removes product from stock
     *
     * @param id identify of product
     */
    void delFromStock(int id);

    /**
     * Gets size of stock
     *
     * @return size of stock
     */
    int getSize();

    /**
     * Gets price of product by id
     *
     * @param id identify of product
     * @return price of product
     */
    int getPriceById(int id);

    /**
     * Gets currency of product by id
     *
     * @param id identify of product
     * @return Currency object
     */
    Currency getCurrencyById(int id);

    /**
     * Gets name of product by id
     *
     * @param id identify of product
     * @return name of product
     */
    String getNameById(int id);
}
