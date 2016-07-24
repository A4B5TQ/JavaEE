package forum.services.userDetails;

import forum.auth.CurrentUser;
import forum.entitys.User;
import forum.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getOneByName(username);
        CurrentUser us = new CurrentUser(user);
        return us;
    }
}
