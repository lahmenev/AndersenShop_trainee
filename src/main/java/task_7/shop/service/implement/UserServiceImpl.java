package task_7.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import task_7.shop.DAO.UserDAO;
import task_7.shop.model.User;
import task_7.shop.service.UserService;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    /**
     * Adds user to database
     *
     * @param user input parameter of user
     */
    @Override
    public void addUser(User user) {
        userDAO.addItem(user);
    }
}
