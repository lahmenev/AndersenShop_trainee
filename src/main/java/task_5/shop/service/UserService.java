package task_5.shop.service;

import task_5.shop.DAO.UserDAO;
import task_5.shop.model.User;
import task_5.shop.utils.DBUtils;
import java.sql.*;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class UserService implements UserDAO {

    /**
     * Adds user to database
     *
     * @param user input parameter of user
     */
    @Override
    public void addUser(User user) {

        try (Connection connection = DBUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement("insert into users (name, password)" +
                     "values (?, ?)")){

            statement.setString(1, user.getName());
            statement.setString(2, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check valid of user
     *
     * @param user input parameter of user
     * @return true if input User equals with user in database
     */
    @Override
    public boolean isSignUpUser(User user) {
        boolean isSign = false;

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from users")) {

            while (rs.next()) {
                String name = rs.getString("name");
                String password = rs.getString("password");

                if (user.getName().equals(name) & user.getPassword().equals(password)) {
                    isSign = true;
                    break;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isSign;
    }
}
