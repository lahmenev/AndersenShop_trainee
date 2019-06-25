package task_7.shop.DAO.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import task_7.shop.DAO.UserDAO;
import task_7.shop.model.Role;
import task_7.shop.model.User;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class UserDAOImpl implements UserDAO {

    @Autowired
    JdbcTemplate template;

    /**
     * Insert item
     *
     * @param user input parameter of user
     */
    @Override
    public void addItem(User user) {
        String userQuery = "insert into users (username, password, enabled) values (?, ?, ?)";
        String user_roleQuery = "insert into user_roles (username, role) values (?, ?)";
        String userName = user.getName();
        String password = user.getPassword();
        String role = Role.USER.toString();

        template.update(userQuery, userName, password, 1);
        template.update(user_roleQuery, userName, role);
    }
}
