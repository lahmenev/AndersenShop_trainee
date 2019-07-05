package task_9.shop.service;

import task_9.shop.model.entity.User;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface UserService {

    /**
     * Adds user to database
     *
     * @param user input parameter of user
     */
    void addUser(User user);
}
