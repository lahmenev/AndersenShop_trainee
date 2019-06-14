package task_2.shop.commands;

import task_2.shop.model.User;
import task_2.shop.model.Product;
import task_2.shop.model.Stock;

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
        int amountInBucket = user.getAmountofProductForBucket();
        Product stockProduct = stock.getStock().get(id);
        int amountInStock = stockProduct.getAmount();

        user.getBucket().addToBucket(id, new Product(stock.getNameById(id),
                   stock.getCurrencyById(id), stock.getPriceById(id), amountInBucket));
        stockProduct.setAmount(amountInStock - amountInBucket);

        if (stockProduct.getAmount() == 0) {
            stock.delFromStock(id);
        }
    }
}
