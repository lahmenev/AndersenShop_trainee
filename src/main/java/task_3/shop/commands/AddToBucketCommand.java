package task_3.shop.commands;

import task_3.shop.model.User;
import task_3.shop.model.Product;
import task_3.shop.model.Stock;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class AddToBucketCommand implements Command {
    private Stock stock;
    private User user;

    public AddToBucketCommand(Stock stock, User user) {
        this.user = user;
        this.stock = stock;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        int id = user.getIdOfProductForBucket();
        int amountInBucket = user.getAmountOfProductForBucket();
        Product stockProduct = stock.getStock().get(id - 1);
        int amountInStock = stockProduct.getAmount();

        user.getBucket().addToBucket(id, user, new Product(stockProduct.getName(),
                stockProduct.getCurrency(), stockProduct.getPrice(), amountInBucket));
        stockProduct.setAmount(amountInStock - amountInBucket);
    }
}
