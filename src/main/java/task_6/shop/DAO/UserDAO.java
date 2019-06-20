package task_6.shop.DAO;

import task_6.shop.model.User;

import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface UserDAO {

    /**
     * Insert item
     *
     * @param user input parameter of user
     */
    void addItem(User user);

    /**
     * Gets list of item
     *
     * @param user input parameter of user
     * @return true if input User equals with user in database
     */
    List<User> getAllItem(User user);
}
