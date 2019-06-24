package task_6.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import task_6.shop.DAO.StockDAO;
import task_6.shop.DAO.implement.StockDAOImpl;
import task_6.shop.exception.ProductTransactionException;
import task_6.shop.model.Product;
import task_6.shop.model.User;
import task_6.shop.service.StockService;

import java.util.List;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class StockServiceImpl implements StockService {

    @Autowired
    StockDAO stockDAO;

    public void setStockDAO(StockDAO stockDAO) {
        this.stockDAO = stockDAO;
    }

    /**
     * Gets list of products from database
     *
     * @return list of products
     */
    @Override
    public List<Product> getStockList() {
        return stockDAO.getAllItem();
    }

    /**
     * Adds product to List
     *
     * @param product item of products
     */
    @Override
    public void addProduct(Product product) {
        stockDAO.insertItem(product);
    }

    /**
     * Removes product
     *
     * @param id input parameter
     */
    @Override
    public void deleteProduct(int id) {
        stockDAO.deleteItem(id);
    }

    /**
     * Adds product to bucket
     *
     * @param product input parameter of Product
     * @param user input parameter of User
     */
    @Override
    public void addToBucket(Product product, User user) throws ProductTransactionException {
        stockDAO.insertToBucket(product, user);
    }
}
