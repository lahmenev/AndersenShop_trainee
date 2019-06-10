package task_3.shop.commands;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class InvokerCommands {
    private Command displayProductsCommand;
    private Command displayBucketCommand;
    private Command addToBucketCommand;
    private Command delFromBucketCommand;
    private Command clearBucketCommand;
    private Command showUserInfo;

    public InvokerCommands(Command displayProductsCommand, Command displayBucketCommand, Command addToBucketCommand,
                           Command delFromBucketCommand, Command clearBucketCommand, Command showUserInfo) {
        this.displayProductsCommand = displayProductsCommand;
        this.displayBucketCommand = displayBucketCommand;
        this.addToBucketCommand = addToBucketCommand;
        this.delFromBucketCommand = delFromBucketCommand;
        this.clearBucketCommand = clearBucketCommand;
        this.showUserInfo = showUserInfo;
    }

    /**
     * Displays products in Stock
     */
    public void displayProducts() {
        displayProductsCommand.execute();
    }

    /**
     * Displays products in bucket
     */
    public void displayBucket() {
        displayBucketCommand.execute();
    }

    /**
     * Adds product into bucket
     */
    public void addToBucket() {
        addToBucketCommand.execute();
    }

    /**
     * Removes product from bucket
     */
    public void delFromBucket() {
        delFromBucketCommand.execute();
    }

    /**
     * Clears bucket
     */
    public void clearBucket() {
        clearBucketCommand.execute();
    }

    public void showUserInfo() {
        showUserInfo.execute();
    }
}
