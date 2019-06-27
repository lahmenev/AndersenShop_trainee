package task_8.shop.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import task_8.shop.model.Role;
import task_8.shop.model.entity.User;
import task_8.shop.repo.UserRepo;
import task_8.shop.service.UserService;
import java.util.Collections;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    /**
     * Adds user to database
     *
     * @param user input parameter of user
     */
    @Override
    public void addUser(User user) {
        user.setEnabled(true);
        user.setRoles(Collections.singleton(Role.ROLE_USER));
        userRepo.save(user);
    }
}
