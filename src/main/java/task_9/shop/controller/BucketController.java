package task_9.shop.controller;

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
import task_9.shop.exception.ProductTransactionException;
import task_9.shop.model.entity.Country;
import task_9.shop.model.entity.EatableProduct;
import task_9.shop.model.entity.UnEatableProduct;
import task_9.shop.service.BucketService;
import task_9.shop.service.StockService;

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
     * Shows form for adding uneatable product to bucket
     *
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/uneatable_product/insert/{id}&{name}&{currency}&{price}&{amount}&{nameCountry}", method = RequestMethod.GET)
    public ModelAndView showAddToBucketUneatableForm(@PathVariable int id, @PathVariable String name,
                                                     @PathVariable String currency, @PathVariable int price,
                                                     @PathVariable int amount, @PathVariable String nameCountry) {
        return new ModelAndView("forms/addToBucketUneatableForm",
                "uneatableProduct", new UnEatableProduct(id, name, currency, price, amount, new Country(nameCountry)));
    }

    /**
     * Shows form for adding eatable product to bucket
     *
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/eatable_product/insert/{id}&{name}&{currency}&{price}&{amount}&{nameCountry}&{shelfLife}", method = RequestMethod.GET)
    public ModelAndView showAddToBucketEatableForm(@PathVariable int id, @PathVariable String name,
                                                   @PathVariable String currency, @PathVariable int price,
                                                   @PathVariable int amount, @PathVariable String nameCountry,
                                                   @PathVariable String shelfLife) {
        return new ModelAndView("forms/addToBucketEatableForm",
                "eatableProduct", new EatableProduct(id, name, currency, price, amount, new Country(nameCountry), shelfLife));
    }

    /**
     * Adds eatable product to bucket
     *
     * @param model Model for storing object and transfer it to representation
     * @param product input object stored in attribute
     * @return view representation
     */
    @RequestMapping(value = "/eatable_product/insert", method = RequestMethod.POST)
    public String addToBucketEatableProduct(Model model, @ModelAttribute("eatableProduct") EatableProduct product) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        try {
            stockService.addToBucketEatableProduct(product, userDetail.getUsername());
        } catch (ProductTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addToBucketEatableForm";
        }

        return "redirect:/products";
    }

    /**
     * Adds uneatable product to bucket
     *
     * @param model Model for storing object and transfer it to representation
     * @param product input object stored in attribute
     * @return view representation
     */
    @RequestMapping(value = "/uneatable_product/insert", method = RequestMethod.POST)
    public String addToBucketUnEatableProduct(Model model, @ModelAttribute("uneatableProduct") UnEatableProduct product) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetail = (UserDetails) auth.getPrincipal();

        try {
            stockService.addToBucketUnEatableProduct(product, userDetail.getUsername());
        } catch (ProductTransactionException e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "addToBucketUneatableForm";
        }

        return "redirect:/products";
    }
}
