package task_8.shop.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import task_8.shop.model.entity.User;

/**
 * email : s.lakhmenev@andersenlab.com
 *
 * @author Lakhmenev Sergey
 * @version 1.1
 */
public interface UserRepo extends JpaRepository<User, Integer> {

    /**
     * Insert item into Database
     *
     * @param user input parameter
     * @return User instance
     */
    @Override
    User save(User user);
}
