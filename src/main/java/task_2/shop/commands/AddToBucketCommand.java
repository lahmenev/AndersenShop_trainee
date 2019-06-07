package task_2.shop.commands;

import task_2.shop.model.Customer;
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
    private Customer customer;

    public AddToBucketCommand(Stock stock, Customer customer) {
        this.customer = customer;
        this.stock = stock;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        int id = customer.getIdOfProductForBucket();
        int amountInBucket = customer.getAmountofProductForBucket();
        Product stockProduct = stock.getStock().get(id);
        int amountInStock = stockProduct.getAmount();

        customer.getBucket().addToBucket(id, new Product(stock.getNameById(id),
                   stock.getCurrencyById(id), stock.getPriceById(id), amountInBucket));
        stockProduct.setAmount(amountInStock - amountInBucket);

        if (stockProduct.getAmount() == 0) {
            stock.delFromStock(id);
        }
    }
}
