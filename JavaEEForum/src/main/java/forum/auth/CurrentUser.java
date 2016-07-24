package forum.auth;

import forum.entitys.Authority;
import forum.entitys.Role;
import forum.entitys.User;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getName(), user.getPassword(), AuthorityUtils.createAuthorityList(
                user.getRoles().stream()
        .map(Authority::getName).toArray(String[]::new)));
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return user.getId();
    }

    public Role[] getRoles(){
        return this.user.getRoles()
                .stream()
                .map(r -> Role.valueOf(r.getName()))
                .toArray(Role[]::new);
    }
}