package task_3.shop.commands;

import task_3.shop.model.User;
import task_3.shop.model.UserInfo;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class ShowUserInfoCommand implements Command{
    private User user;
    private UserInfo userInfo = new UserInfo();

    public ShowUserInfoCommand(User user) {
        this.user = user;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        userInfo.getUserInformation(user);
    }
}
