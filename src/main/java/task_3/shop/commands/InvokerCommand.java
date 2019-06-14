package task_3.shop.commands;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface InvokerCommand {

    /**
     * Displays products in Stock
     */
    void displayProducts();

    /**
     * Displays products in bucket
     */
    void displayBucket();

    /**
     * Adds product into bucket
     */
    void addToBucket();

    /**
     * Removes product from bucket
     */
    void delFromBucket();

    /**
     * Clears bucket
     */
    void clearBucket();

    /**
     * Displays user information
     */
    void showUserInfo();
}
