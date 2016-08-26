package issueTracker.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {

    private long id;
    private String fullname;
    private String username;
    private String password;
    private String email;

    private List<Project> projects;
    private Set<Issue> issues;
    private Set<Authority> authorities;

    public User() {
        this.authorities = new HashSet<>();
        this.projects = new ArrayList<>();
        this.issues = new HashSet<>();
    }

    public User(String fullname, String username, String password, String email) {
        this.authorities = new HashSet<>();
        this.projects = new ArrayList<>();
        this.issues = new HashSet<>();
        this.setFullname(fullname);
        this.setPassword(password);
        this.setUsername(username);
        this.setEmail(email);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_issues",
    joinColumns = {@JoinColumn(name = "user_id")},
    inverseJoinColumns = {@JoinColumn(name = "issue_id")})
    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "assigneeUsers")
    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
}
