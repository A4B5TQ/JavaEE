package forum.entitys;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements Serializable {

    public User() {
    }

    private int id;
    private String name;
    private String password;
    private Set<Answer> answers = new HashSet<>(0);
    private Set<Topic> topics = new HashSet<>(0);
    private Set<Authority> roles = new HashSet<>(0);


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    public Set<Topic> getTopics() {
        return topics;
    }

    public void setTopics(Set<Topic> topics) {
        this.topics = topics;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    public Set<Authority> getRoles() {
        return roles;
    }

    public void setRoles(Set<Authority> roles) {
        this.roles = roles;
    }
}
