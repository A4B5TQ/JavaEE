package issueTracker.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "issues")
public class Issue {

    private long id;
    private String title;
    private String description;
    private Project project;
    private Type type;
    private Set<User> assigneeUsers;
    private Priority priority;
    private State currentState;

    public Issue() {
    }

    public Issue(String title, String description, Project project,Priority priority, State currentState) {
        this.setTitle(title);
        this.setDescription(description);
        this.setProject(project);
        this.assigneeUsers = new HashSet<>();
        this.setPriority(priority);
        this.setCurrentState(currentState);
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "issues")
    public Set<User> getAssigneeUsers() {
        return assigneeUsers;
    }

    public void setAssigneeUsers(Set<User> assigneeUsers) {
        this.assigneeUsers = assigneeUsers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "priority_id")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id", nullable = false)
    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}

