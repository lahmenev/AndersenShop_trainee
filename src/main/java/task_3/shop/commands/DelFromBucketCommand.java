package task_3.shop.commands;

import task_3.shop.model.User;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class DelFromBucketCommand implements Command {
    private User user;

    public DelFromBucketCommand(User user) {
        this.user = user;
    }

    /**
     * Executes command
     */
    @Override
    public void execute() {
        int id = user.getIdOfProductForBucket();
        user.getBucket().delFromBucket(id);
    }
}
