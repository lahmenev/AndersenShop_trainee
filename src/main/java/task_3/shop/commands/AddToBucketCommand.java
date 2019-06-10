package task_3.shop.commands;

import task_3.shop.model.Customer;
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
        int amountInBucket = customer.getAmountOfProductForBucket();
        Product stockProduct = stock.getStock().get(id - 1);
        int amountInStock = stockProduct.getAmount();

        customer.getBucket().addToBucket(id, customer, new Product(stockProduct.getName(),
                stockProduct.getCurrency(), stockProduct.getPrice(), amountInBucket));
        stockProduct.setAmount(amountInStock - amountInBucket);
    }
}
