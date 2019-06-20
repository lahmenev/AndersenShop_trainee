package task_7.shop.DAO;

import task_7.shop.model.User;

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
}
