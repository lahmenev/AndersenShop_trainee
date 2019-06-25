package task_7.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import task_7.shop.model.User;
import task_7.shop.service.UserService;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * Shows registration form
     *
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationForm() {
        return new ModelAndView("registrationForm", "user", new User());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting() {
        return "welcomePage";
    }

    /**
     * Signs up new user
     *
     * @param user input object stored in attribute
     * @return view representation
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String signUp(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/";
    }
}
