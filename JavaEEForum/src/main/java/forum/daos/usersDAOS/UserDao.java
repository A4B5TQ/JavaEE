package forum.daos.usersDAOS;

import forum.entitys.Authority;
import forum.entitys.User;

public interface UserDao {
    void create(User user, Authority authority);
    User getOneById(int id);
    User getOneByNameAndPassword(String username,String password);
    User getOneByName(String username);
}
