package task_6.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import task_6.shop.exception.ProductTransactionException;
import task_6.shop.model.Product;
import task_6.shop.model.User;
import task_6.shop.service.BucketService;
import task_6.shop.service.StockService;
import javax.servlet.http.HttpSession;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
@Controller
public class ProductOperationsController {

    @Autowired
    StockService stockService;

    @Autowired
    BucketService bucketService;

    /**
     * Shows products
     *
     * @param model Model for storing object and transfer it to representation
     * @return view representation
     */
    @RequestMapping(value = "/productList", method = RequestMethod.GET)
    public String showProductList(Model model) {
        model.addAttribute("productList", stockService.getStockList());
        return "productList";
    }

    /**
     * Shows form for adding new product
     *
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/createProduct", method = RequestMethod.GET)
    public ModelAndView showProductForm() {
        return new ModelAndView("productForm", "product", new Product());
    }

    /**
     * Adds new product
     *
     * @param product input object stored in attribute
     * @return view representation
     */
    @RequestMapping(value = "/createProduct", method = RequestMethod.POST)
    public String insertProduct(@ModelAttribute("product") Product product) {
        stockService.addProduct(product);
        return "redirect:/productList";
    }

    /**
     * Removes product from list
     *
     * @param id input parameter from view
     * @return view representation
     */
    @RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable int id) {
        stockService.deleteProduct(id);
        return "redirect:/productList";
    }

    /**
     * Adds product to bucket
     *
     * @param model Model for storing object and transfer it to representation
     * @param product input object stored in attribute
     * @param session input parameter of session
     * @return view representation
     */
    @RequestMapping(value = "/addToBucket/add", method = RequestMethod.POST)
    public String addToBucket(Model model, @ModelAttribute("product") Product product, HttpSession session) {
        User user = (User) session.getAttribute("user");

        try {
            stockService.addToBucket(product, user);
        } catch (ProductTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addToBucketForm";
        }
        return "redirect:/productList";
    }

    /**
     * Shows products in bucket
     *
     * @param model Model for storing object and transfer it to representation
     * @param session input parameter of session
     * @return view representation
     */
    @RequestMapping(value = "/bucketList", method = RequestMethod.GET)
    public String showBucketList(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("bucketList", bucketService.getBucketList(user));
        return "bucketList";
    }

    /**
     * Shows form for adding product to bucket
     *
     * @param id input parameter from view
     * @param name input parameter from view
     * @param currency input parameter from view
     * @param price input parameter from view
     * @param amount input parameter from view
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/addToBucket/{id}&{name}&{currency}&{price}&{amount}", method = RequestMethod.GET)
    public ModelAndView showAddToBucketForm(@PathVariable int id, @PathVariable String name, @PathVariable String currency,
                                            @PathVariable int price, @PathVariable int amount) {
        return new ModelAndView("addToBucketForm", "product", new Product(id, name, currency, price, amount));
    }
}
