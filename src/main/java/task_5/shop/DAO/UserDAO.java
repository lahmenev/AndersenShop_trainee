package task_5.shop.DAO;

import task_5.shop.model.User;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface UserDAO {

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
