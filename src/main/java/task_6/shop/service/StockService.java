package task_6.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import task_6.shop.DAO.StockDAO;
import task_6.shop.DAO.implement.StockDAOImpl;
import task_6.shop.exception.ProductTransactionException;
import task_6.shop.model.Product;
import task_6.shop.model.User;
import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class StockService {
    private StockDAO stockDAO = new StockDAOImpl();

    /**
     * Gets list of products from database
     *
     * @return list of products
     */
    public List<Product> getStockList() {
        return stockDAO.getAllItem();
    }

    /**
     * Adds product to List
     *
     * @param product item of products
     */
    public void addProduct(Product product) {
        stockDAO.insertItem(product);
    }

    /**
     * Removes product
     *
     * @param id input parameter
     */
    public void deleteProduct(int id) {
        stockDAO.deleteItem(id);
    }

    /**
     * Adds product to bucket
     *
     * @param product input parameter of Product
     * @param user input parameter of User
     */
    public void addToBucket(Product product, User user) throws ProductTransactionException {
        stockDAO.insertToBucket(product, user);
    }
}
