package task_6.shop.service;

import task_6.shop.DAO.UserDAO;
import task_6.shop.DAO.implement.UserDAOImpl;
import task_6.shop.model.User;
import java.util.List;

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

    /**
     * Check valid of user
     *
     * @param user input parameter of user
     * @return true if input User equals with user in database
     */
    public boolean isSignUpUser(User user) {
        boolean isSign = false;

        List<User> users = userDAO.getAllItem(user);

        if (users.size() > 0) {
            isSign = true;
        }

        return isSign;
    }
}
