package forum.entitys;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic {

    private int id;
    private String title;
    private String text;
    private Date createDate;

    private User author;
    private Category category;

    private Set<Answer> answers = new HashSet<>(0);

    public Topic() {
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic")
    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorID",nullable = false)
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categorieID",nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
