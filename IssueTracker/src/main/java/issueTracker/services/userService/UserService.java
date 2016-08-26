package issueTracker.services.userService;

import issueTracker.dtos.UserAddForm;
import issueTracker.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void createUser(UserAddForm userAddForm);
    User findOneById(Long id);
}
