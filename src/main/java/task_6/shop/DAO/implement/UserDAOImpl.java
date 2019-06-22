package task_6.shop.DAO.implement;

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
        String sqlQuery = "insert into users1 (name, password) values (?, ?)";
        String name = user.getName();
        String password = user.getPassword();

        template.update(sqlQuery, name, password);
    }

    /**
     * Gets list of item
     *
     * @param user input parameter of user
     * @return true if input User equals with user in database
     */
    @Override
    public List<User> getAllItem(User user) {
        String sqlQuery = "select * from users1 where name = ? and password = ?";

        RowMapper<User> userRowMapper = (rs, userItem) -> {
            User userMapper = new User();
            String name = rs.getString("name");
            String password = rs.getString("password");
            userMapper.setName(name);
            userMapper.setPassword(password);

            return userMapper;
        };

        List<User> users = template.query(sqlQuery, userRowMapper, user.getName(), user.getPassword());
        return users;
    }
}
