package shop.task_2.commands;

import shop.task_2.model.Customer;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class ClearBucketCommand implements Command {
    private Customer customer;

    public ClearBucketCommand(Customer customer) {
        this.customer = customer;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        customer.getBucket().clearBucket();
    }
}
