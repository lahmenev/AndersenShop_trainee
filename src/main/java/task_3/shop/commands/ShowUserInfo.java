package task_3.shop.commands;

import task_3.shop.model.Customer;
import task_3.shop.model.UserInfo;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class ShowUserInfo implements Command{
    private Customer customer;
    private UserInfo userInfo = new UserInfo();

    public ShowUserInfo(Customer customer) {
        this.customer = customer;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        userInfo.getUserInformation(customer);
    }
}
