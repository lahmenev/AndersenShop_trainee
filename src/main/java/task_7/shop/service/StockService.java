package task_7.shop.service;

import task_7.shop.DAO.StockDAO;
import task_7.shop.DAO.implement.StockDAOImpl;
import task_7.shop.exception.ProductTransactionException;
import task_7.shop.model.Product;
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
     * @param userName input parameter of User
     */
    public void addToBucket(Product product, String userName) throws ProductTransactionException {
        stockDAO.insertToBucket(product, userName);
    }
}
