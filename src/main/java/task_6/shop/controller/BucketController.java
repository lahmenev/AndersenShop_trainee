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
@RequestMapping(value = "/bucket")
public class BucketController {

    @Autowired
    BucketService bucketService;

    @Autowired
    StockService stockService;

    /**
     * Shows products in bucket
     *
     * @param model Model for storing object and transfer it to representation
     * @param session parameter for storing user in Session
     * @return view representation
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showBucketList(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        model.addAttribute("bucketList", bucketService.getBucketList(user));
        return "bucketList";
    }

    /**
     * Shows form for adding product to bucket
     *
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/insert/{id}&{name}&{currency}&{price}&{amount}", method = RequestMethod.GET)
    public ModelAndView showAddToBucketForm(@PathVariable int id, @PathVariable String name, @PathVariable String currency,
                                            @PathVariable int price, @PathVariable int amount) {
        return new ModelAndView("addToBucketForm", "product", new Product(id, name, currency, price, amount));
    }

    /**
     * Adds product to bucket
     *
     * @param model Model for storing object and transfer it to representation
     * @param product input object stored in attribute
     * @param session parameter for storing user in Session
     * @return view representation
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String addToBucket(Model model, @ModelAttribute("product") Product product, HttpSession session) {
        User user = (User) session.getAttribute("user");

        try {
            stockService.addToBucket(product, user);
        } catch (ProductTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "insert";
        }
        return "redirect:/products";
    }
}
