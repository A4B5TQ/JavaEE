package issueTracker.auth;

import issueTracker.entities.Authority;
import issueTracker.entities.User;
import issueTracker.enums.Role;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(
                user.getAuthorities().stream()
        .map(Authority::getName).toArray(String[]::new)));
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    public long getId() {
        return user.getId();
    }

    public Role[] getRoles(){
        return this.user.getAuthorities()
                .stream()
                .map(r -> Role.valueOf(r.getName()))
                .toArray(Role[]::new);
    }
}