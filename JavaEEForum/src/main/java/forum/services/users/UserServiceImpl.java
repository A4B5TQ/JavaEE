package forum.services.users;

import forum.daos.usersDAOS.UserDao;
import forum.dtos.UserAddForm;
import forum.entitys.Authority;
import forum.entitys.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void create(UserAddForm form) {
        BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
        Authority authority = new Authority();
        User user = new User();
        user.setName(form.getUsername());
        user.setPassword(passEncoder.encode(form.getPassword()));
        authority.setName(form.getRole().toString());
        authority.setUser(user);
        user.getRoles().add(authority);
        userDao.create(user,authority);
    }

    @Override
    public User getOneById(int id) {
       User user = this.userDao.getOneById(id);
        return user;
    }

    @Override
    public User getOneByNameAndPassword(String username, String password) {
        User user = userDao.getOneByNameAndPassword(username,password);

        return user;

    }

    @Override
    public User getOneByName(String username) {
        return this.userDao.getOneByName(username);
    }
}
