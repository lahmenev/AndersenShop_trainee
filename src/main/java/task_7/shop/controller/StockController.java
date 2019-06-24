package task_7.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import task_7.shop.model.Product;
import task_7.shop.service.StockService;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
@Controller
@RequestMapping(value = "/products")
public class StockController {

    @Autowired
    StockService stockService;

    /**
     * Shows products
     *
     * @param model Model for storing object and transfer it to representation
     * @return view representation
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(Model model) {
        model.addAttribute("productList", stockService.getStockList());
        return "productList";
    }

    /**
     * Shows form for adding new product
     *
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public ModelAndView showProductForm() {
        return new ModelAndView("productForm", "product", new Product());
    }

    /**
     * Adds new product
     *
     * @param product input object stored in attribute
     * @return view representation
     */
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insertProduct(@ModelAttribute("product") Product product) {
        stockService.addProduct(product);
        return "redirect:/products";
    }

    /**
     * Removes product from list
     *
     * @param id input parameter from view
     * @return view representation
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteProduct(@PathVariable int id) {
        stockService.deleteProduct(id);
        return "redirect:/products";
    }

    /**
     * Removes product from list
     *
     * @param id input parameter from view
     * @return view representation
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String productsAfterDelete(@PathVariable int id) {
        stockService.deleteProduct(id);
        return "redirect:/products";
    }
}
