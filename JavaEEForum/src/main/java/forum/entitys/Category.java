package forum.entitys;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category implements Serializable {

    private int id;
    private String name;
    private Set<Topic> topics = new HashSet<>(0);

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    public Set<Topic> getTopics() {
        return topics;
    }
}
