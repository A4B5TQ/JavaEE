package forum.dtos;

import forum.entitys.Authority;
import forum.entitys.Role;

public class UserAddForm {

    private String username;
    private String password;
    private Authority authority;
    private Role role = Role.USER;

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority role) {
        this.authority = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole(){
        return this.role;
    }

    public void setRole(Role role){
        this.role = role;
    }

}
