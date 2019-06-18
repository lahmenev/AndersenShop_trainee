package task_6.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import task_6.shop.DAO.BucketDAO;
import task_6.shop.model.BucketItem;
import task_6.shop.model.User;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class BucketService implements BucketDAO {

    @Autowired
    JdbcTemplate template;

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    /**
     * Gets list of bucket from database
     *
     * @param user input parameter of user
     * @return List of BucketItem objects
     */
    @Override
    public List<BucketItem> getBucketList(User user) {
        String userName = user.getName();
        String sqlQuery = "select * from orders where userName = '"+ userName +"'";

        RowMapper<BucketItem> rowMapper = (rs, row) -> {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String currency = rs.getString("currency");
            int price = rs.getInt("price");
            int amount = rs.getInt("amount");
            int sum = rs.getInt("summ");

            return new BucketItem(id, name, currency, price, amount, sum);
        };

        return template.query(sqlQuery, rowMapper);
    }
}
