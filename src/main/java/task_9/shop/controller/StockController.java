package task_9.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import task_9.shop.model.entity.EatableProduct;
import task_9.shop.model.entity.UnEatableProduct;
import task_9.shop.service.ProductService;

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
    ProductService<EatableProduct> eatableProductService;

    @Autowired
    ProductService<UnEatableProduct> unEatableProductService;

    /**
     * Shows products
     *
     * @param model Model for storing object and transfer it to representation
     * @return view representation
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showProductList(Model model) {
        model.addAttribute("eatable_productList", eatableProductService.getProductList());
        model.addAttribute("uneatable_productList", unEatableProductService.getProductList());
        return "productList";
    }

    /**
     * Shows form for adding eatable product
     *
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/eatable_product/insert", method = RequestMethod.GET)
    public ModelAndView showEatableProductForm() {
        return new ModelAndView("eatable_productForm", "eatableProduct", new EatableProduct());
    }

    /**
     * Adds eatable product
     *
     * @param eatableProduct input object stored in attribute
     * @return view representation
     */
    @RequestMapping(value = "/eatable_product/insert", method = RequestMethod.POST)
    public String insertEatableProduct(@ModelAttribute("eatableProduct") EatableProduct eatableProduct) {
        eatableProductService.addProduct(eatableProduct);
        return "redirect:/products";
    }

    /**
     * Shows form for adding uneatable product
     *
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/uneatable_product/insert", method = RequestMethod.GET)
    public ModelAndView showUnEatableProductForm() {
        return new ModelAndView("un_eatable_productForm", "uneatableProduct", new UnEatableProduct());
    }

    /**
     * Adds uneatable product
     *
     * @param uneatableProduct input object stored in attribute
     * @return view representation
     */
    @RequestMapping(value = "/uneatable_product/insert", method = RequestMethod.POST)
    public String insertUnEatableProduct(@ModelAttribute("uneatableProduct") UnEatableProduct uneatableProduct) {
        unEatableProductService.addProduct(uneatableProduct);
        return "redirect:/products";
    }

    /**
     * Removes eatable product from list
     *
     * @param id input parameter from view
     * @return view representation
     */
    @RequestMapping(value = "/eatable_product/{id}", method = RequestMethod.DELETE)
    public String deleteEatableProduct(@PathVariable int id) {
        eatableProductService.deleteProduct(id);
        return "redirect:/products";
    }

    /**
     * Removes eatable product from list
     *
     * @param id input parameter from view
     * @return view representation
     */
    @RequestMapping(value = "/eatable_product/{id}", method = RequestMethod.GET)
    public String eatableProductsAfterDelete(@PathVariable int id) {
        eatableProductService.deleteProduct(id);
        return "redirect:/products";
    }

    /**
     * Removes uneatable product from list
     *
     * @param id input parameter from view
     * @return view representation
     */
    @RequestMapping(value = "/uneatable_product/{id}", method = RequestMethod.DELETE)
    public String deleteUnEatableProduct(@PathVariable int id) {
        unEatableProductService.deleteProduct(id);
        return "redirect:/products";
    }

    /**
     * Removes uneatable product from list
     *
     * @param id input parameter from view
     * @return view representation
     */
    @RequestMapping(value = "/uneatable_product/{id}", method = RequestMethod.GET)
    public String UneatableProductsAfterDelete(@PathVariable int id) {
        unEatableProductService.deleteProduct(id);
        return "redirect:/products";
    }

    /**
     * Gets information about eatable product
     *
     * @param id input parameter of identify product
     * @param model input parameter of Model
     * @return representation of view
     */
    @RequestMapping(value = "/eatable_product/product-info/{id}", method = RequestMethod.GET)
    public String getEatableProductInfo(@PathVariable int id, Model model) {
        model.addAttribute("productInfo", eatableProductService.getProductInfo(id));
        return "productInfo";
    }

    /**
     * Gets information about uneatable product
     *
     * @param id input parameter of identify product
     * @param model input parameter of Model
     * @return representation of view
     */
    @RequestMapping(value = "/uneatable_product/product-info/{id}", method = RequestMethod.GET)
    public String getUnEatableProductInfo(@PathVariable int id, Model model) {
        model.addAttribute("productInfo", unEatableProductService.getProductInfo(id));
        return "productInfo";
    }
}
