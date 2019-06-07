package task_2.shop.commands;

import task_2.shop.model.Customer;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class DisplayBucketCommand implements Command {
    private Customer customer;

    public DisplayBucketCommand(Customer customer) {
        this.customer = customer;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        int id = customer.getIdOfProductForBucket();
        customer.getBucket().displayBucket(id);
    }
}
