package issueTracker.entities;

import issueTracker.enums.Visibility;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "states")
public class State {
    private static final Visibility DEFAULT_VISIBILITY = Visibility.LOCALE;

    private long id;
    private String name;
    private Visibility visibility;
    private List<Issue> issues;
    private List<Project> projects;

    public State() {
    }

    public State(String name) {
        this.setName(name);
        this.projects = new ArrayList<>();
        this.issues = new ArrayList<>();
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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "currentState")
    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public static Visibility getDefaultVisibility() {
        return DEFAULT_VISIBILITY;
    }

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "states")
    public List<Project> getProject() {
        return this.projects;
    }

    public void setProject(List<Project> project) {
        this.projects = project;
    }
}
