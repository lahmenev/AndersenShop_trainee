package task_2.shop.commands;

import task_2.shop.model.Stock;
import task_2.shop.model.User;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class InvokerCommandImpl implements InvokerCommand {
    private Command displayProductsCommand;
    private Command displayBucketCommand;
    private Command addToBucketCommand;
    private Command delFromBucketCommand;
    private Command clearBucketCommand;

    public InvokerCommandImpl(User user, Stock stock) {
        this.displayProductsCommand = new DisplayProductsCommand(stock);
        this.displayBucketCommand = new DisplayBucketCommand(user);
        this.addToBucketCommand = new AddToBucketCommand(stock, user);
        this.delFromBucketCommand = new DelFromBucketCommand(user);
        this.clearBucketCommand = new ClearBucketCommand(user);
    }

    /**
     * Displays products in Stock
     */
    @Override
    public void displayProducts() {
        displayProductsCommand.execute();
    }

    /**
     * Displays products in bucket
     */
    @Override
    public void displayBucket() {
        displayBucketCommand.execute();
    }

    /**
     * Adds product into bucket
     */
    @Override
    public void addToBucket() {
        addToBucketCommand.execute();
    }

    /**
     * Removes product from bucket
     */
    @Override
    public void delFromBucket() {
        delFromBucketCommand.execute();
    }

    /**
     * Clears bucket
     */
    @Override
    public void clearBucket() {
        clearBucketCommand.execute();
    }
}
