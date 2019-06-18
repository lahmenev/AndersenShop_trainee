package task_6.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import task_6.shop.DAO.UserDAO;
import task_6.shop.model.User;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class UserService implements UserDAO {

    @Autowired
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Adds user to database
     *
     * @param user input parameter of user
     */
    @Override
    public void addUser(User user) {
        String sqlQuery = "insert into users (name, password) values (?, ?))";
        String name = user.getName();
        String password = user.getPassword();

        template.update(sqlQuery, name, password);
    }

    /**
     * Check valid of user
     *
     * @param user input parameter of user
     * @return true if input User equals with user in database
     */
    @Override
    public boolean isSignUpUser(User user) {
        String sqlQuery = "select * from users where name = ? and password = ?";
        boolean isSign = false;

        RowMapper<User> rowMapper = (rs, row) -> {
            User userMapper = new User();
            String name = rs.getString("name");
            String password = rs.getString("password");
            userMapper.setName(name);
            userMapper.setPassword(password);

            return userMapper;
        };

        List<User> users = template.query(sqlQuery, rowMapper, user.getName(), user.getPassword());

        if (users.size() > 0) {
            isSign = true;
        }

        return isSign;
    }
}
