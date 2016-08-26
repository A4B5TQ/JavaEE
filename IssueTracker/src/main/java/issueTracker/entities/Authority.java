package issueTracker.entities;

import javax.persistence.*;

@Entity
@Table(name = "authorities")
public class Authority {
    private long id;
    private String name;
    private User user;

    public Authority() {
    }

    public Authority(String name, User user) {
        this.setName(name);
        this.setUser(user);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}