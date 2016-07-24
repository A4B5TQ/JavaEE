package forum.services.users;

import forum.dtos.UserAddForm;
import forum.entitys.User;

public interface UserService {
    void create(UserAddForm form);
    User getOneById(int id);
    User getOneByNameAndPassword(String username, String password);
    User getOneByName(String username);
}
