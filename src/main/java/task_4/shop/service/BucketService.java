package task_4.shop.service;

import task_4.shop.DAO.BucketDAO;
import task_4.shop.model.BucketItem;
import task_4.shop.utils.DBUtils;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class BucketService implements BucketDAO {

    /**
     * Gets list of bucket from database
     *
     * @return List of Bucket
     */
    @Override
    public List<BucketItem> getBucketList() {
        List<BucketItem> bucketList = new ArrayList<>();

        try (Connection connection = DBUtils.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery("select * from orders")) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String currancy = rs.getString("currancy");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                int sum = rs.getInt("summ");

                BucketItem bucketItem = new BucketItem(id, name, currancy, price, amount, sum);
                bucketList.add(bucketItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bucketList;
    }
}
