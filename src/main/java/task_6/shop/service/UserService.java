package task_6.shop.service;

import task_6.shop.model.User;

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

    /**
     * Check valid of user
     *
     * @param user input parameter of user
     * @return true if input User equals with user in database
     */
    boolean isSignUpUser(User user);
}
