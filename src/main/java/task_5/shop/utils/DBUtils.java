package task_5.shop.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class DBUtils {

    /**
     * Gets connection with database
     *
     * @return object of Connection
     */
    public static Connection getConnection() {
        String userName = "root";
        String password = "1q2w3e4r5t6y";
        String connectionURL = "jdbc:mysql://localhost:3306/onlineshop?useLegacyDatetimeCode=false&amp&serverTimezone=UTC";
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionURL, userName, password);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
