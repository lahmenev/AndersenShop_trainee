package task_7.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import task_7.shop.exception.ProductTransactionException;
import task_7.shop.model.Product;
import task_7.shop.service.BucketService;
import task_7.shop.service.StockService;
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
     * @return view representation
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showBucketList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        model.addAttribute("bucketList", bucketService.getBucketList(userDetail.getUsername()));
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
     * @param session input parameter of session
     * @return view representation
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String addToBucket(Model model, @ModelAttribute("product") Product product, HttpSession session) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        try {
            stockService.addToBucket(product, userDetail.getUsername());
        } catch (ProductTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addToBucketForm";
        }
        return "redirect:/productList";
    }
}
