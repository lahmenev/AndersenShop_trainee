package task_6.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import task_6.shop.model.User;
import task_6.shop.service.UserService;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
@Controller
@SessionAttributes("user")
public class LoginController {

    @Autowired
    UserService userService;

    /**
     * Shows login form
     *
     * @return view representation with model model attribute
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView showLoginForm() {
        return new ModelAndView("loginForm", "user", new User());

    }

    /**
     * Signs in user.
     *
     * @param user input object stored in attribute
     * @return view representation
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String signIn(@ModelAttribute("user") User user) {
        String view = "loginForm";

        if (userService.isSignUpUser(user)) {
            view = "redirect:/products";
        }

        return view;
    }

    /**
     * Shows registration form
     *
     * @return view representation with model attribute
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView showRegistrationForm() {
        return new ModelAndView("registrationForm", "user", new User());
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
        return "redirect:/login";
    }

    /**
     * Log out current user
     *
     * @param sessionStatus input parameter for close session
     * @return view representation
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOut(SessionStatus sessionStatus) {
        sessionStatus.setComplete();
        return "redirect:/login";
    }
}
