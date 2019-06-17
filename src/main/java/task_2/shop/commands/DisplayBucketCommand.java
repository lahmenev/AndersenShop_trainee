package task_2.shop.commands;

import task_2.shop.model.User;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class DisplayBucketCommand implements Command {
    private User user;

    public DisplayBucketCommand(User user) {
        this.user = user;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        int id = user.getIdOfProductForBucket();
        user.getBucket().displayBucket(id);
    }
}
