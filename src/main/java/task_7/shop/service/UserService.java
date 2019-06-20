package task_7.shop.service;

import task_7.shop.DAO.UserDAO;
import task_7.shop.DAO.implement.UserDAOImpl;
import task_7.shop.model.User;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class UserService {
    private UserDAO userDAO = new UserDAOImpl();

    /**
     * Adds user to database
     *
     * @param user input parameter of user
     */
    public void addUser(User user) {
        userDAO.addItem(user);
    }
}
