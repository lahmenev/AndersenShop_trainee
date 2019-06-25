package task_7.shop.DAO.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import task_7.shop.DAO.BucketDAO;
import task_7.shop.model.BucketItem;
import task_7.shop.model.Product;

import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class BucketDAOImpl implements BucketDAO {

    @Autowired
    JdbcTemplate template;

    /**
     * Gets list of item
     *
     * @param userName input parameter of user
     * @return List of BucketItem objects
     */
    @Override
    public List<BucketItem> getAllItem(String userName) {
        String sqlQuery = "select * from orders where userName = '"+ userName +"'";

        RowMapper<BucketItem> bucketItemRowMapper = (rs, bucketItem) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String currency = rs.getString("currency");
            int price = rs.getInt("price");
            int amount = rs.getInt("amount");
            int sum = rs.getInt("summ");

            Product product = new Product(id, name, currency, price, amount);
            return new BucketItem(product, sum);
        };

        return template.query(sqlQuery, bucketItemRowMapper);
    }
}
