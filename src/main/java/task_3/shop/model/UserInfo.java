package task_3.shop.model;

import task_3.shop.service.DBConnection;
import java.sql.*;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class UserInfo {

    private static Date date;
    private static int order_id;
    private static int summOrder;

    /**
     * Gets user information
     *
     * @param customer input parameter of user
     */
    public void getUserInformation(Customer customer) {
        Bucket bucket = new Bucket();
        String currancy;
        int user_id = customer.getId();
        int price;
        int amount;
        int finalPrice = 0;

        try (Connection connection = DBConnection.getConnection();
             CallableStatement statement = connection.prepareCall("{call userInfo(?)}")) {
            statement.setInt(1, user_id);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                order_id = rs.getInt("id");
                date = rs.getDate("date");
                summOrder = rs.getInt("summ");

                price = rs.getInt("price");
                amount = rs.getInt("amount");
                currancy = rs.getString("currancy");
                finalPrice += bucket.paymentFinalPrice(currancy, price, amount);
                System.out.printf("order_id = %d , date = %tF, sum of order = %d\n",order_id, date, summOrder);
            }

            System.out.printf("Final price for all orders: %d\n", finalPrice);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Date getDate() {
        return date;
    }

    public static int getOrder_id() {
        return order_id;
    }

    public static int getSummOrder() {
        return summOrder;
    }
}
