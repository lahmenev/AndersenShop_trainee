package task_5.shop.controller;

import task_5.shop.model.User;
import task_5.shop.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/login":
                showLoginForm(req, resp);
                break;
            case "/signIn":
                signIn(req, resp);
                break;
            case "/registration":
                showRegistrationForm(req, resp);
                break;
            case "/signUp":
                createNewUser(req, resp);
                break;
            case "/signOut":
                signOut(req, resp);
                break;
        }
    }

    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/signIn.jsp").forward(req, resp);
    }

    private void signIn(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        User user = new User(name, password);

        if (userService.isSignUpUser(user)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("list");
        }

    }

    private void showRegistrationForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/signUp.jsp").forward(req, resp);
    }

    private void createNewUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        User user = new User(name, password);
        userService.addUser(user);
        resp.sendRedirect("login");
    }

    private void signOut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        req.getRequestDispatcher("/WEB-INF/pages/signIn.jsp").forward(req, resp);
    }
}
