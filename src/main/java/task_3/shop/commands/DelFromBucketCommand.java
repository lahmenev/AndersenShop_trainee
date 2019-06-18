package task_3.shop.commands;

import task_3.shop.model.Customer;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class DelFromBucketCommand implements Command {
    private Customer customer;

    public DelFromBucketCommand(Customer customer) {
        this.customer = customer;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        int id = customer.getIdOfProductForBucket();
        customer.getBucket().delFromBucket(id);
    }
}