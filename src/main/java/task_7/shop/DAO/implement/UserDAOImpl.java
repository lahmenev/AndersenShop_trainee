package task_7.shop.DAO.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import task_7.shop.DAO.UserDAO;
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

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Insert item
     *
     * @param user input parameter of user
     */
    @Override
    public void addItem(User user) {
        String sqlQuery = "insert into users (name, password) values (?, ?))";
        String name = user.getName();
        String password = user.getPassword();

        template.update(sqlQuery, name, password);
    }
}
