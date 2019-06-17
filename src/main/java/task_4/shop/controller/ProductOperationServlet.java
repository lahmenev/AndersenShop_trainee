package task_4.shop.controller;

import task_4.shop.DAO.BucketDAO;
import task_4.shop.DAO.StockDAO;
import task_4.shop.model.Product;
import task_4.shop.service.BucketService;
import task_4.shop.service.StockService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class ProductOperationServlet extends HttpServlet {
    private StockDAO stockDAO = new StockService();
    private BucketDAO bucketDAO = new BucketService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/list":
                listProducts(req, resp);
                break;
            case "/delete":
                deleteProduct(req, resp);
                break;
            case "/new":
                showNewForm(req, resp);
                break;
            case "/insert":
                insertProduct(req, resp);
                break;
            case "/bucket":
                listBucket(req, resp);
                break;
            case "/addBucket":
                addToBucket(req, resp);
        }
    }

    /**
     * Gets list of products and set it to attribute
     *
     * @param req request parameter
     * @param resp response parameter
     * @throws ServletException
     * @throws IOException
     */
    private void listProducts(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("allProducts", stockDAO.getAllProducts());
        req.getRequestDispatcher("/WEB-INF/pages/productList.jsp").forward(req, resp);
    }

    /**
     * Removes product by id
     *
     * @param req request parameter
     * @param resp response parameter
     * @throws ServletException
     * @throws IOException
     */
    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = new Product(id);
        stockDAO.deleteProduct(product);
        resp.sendRedirect("list");

    }

    /**
     * Opens form to add new product
     *
     * @param req request parameter
     * @param resp response parameter
     * @throws ServletException
     * @throws IOException
     */
    private void showNewForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/productForm.jsp").forward(req, resp);
    }

    /**
     * Adds new product
     *
     * @param req request parameter
     * @param resp response parameter
     * @throws ServletException
     * @throws IOException
     */
    private void insertProduct(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String currancy = req.getParameter("currancy");
        int price = Integer.parseInt(req.getParameter("price"));
        int amount = Integer.parseInt(req.getParameter("amount"));
        Product product = new Product(name, currancy, price, amount);
        stockDAO.addProduct(product);
        resp.sendRedirect("list");
    }

    /**
     * Gets bucket list and set it to attribute
     *
     * @param req request parameter
     * @param resp response parameter
     * @throws ServletException
     * @throws IOException
     */
    private void listBucket(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("bucketList", bucketDAO.getBucketList());
        req.getRequestDispatcher("/WEB-INF/pages/bucketList.jsp").forward(req, resp);
    }

    /**
     * Adds product to bucket
     *
     * @param req request parameter
     * @param resp response parameter
     * @throws ServletException
     * @throws IOException
     */
    private void addToBucket(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = new Product(id);
        stockDAO.addToBucket(product);
        resp.sendRedirect("list");
    }
}
