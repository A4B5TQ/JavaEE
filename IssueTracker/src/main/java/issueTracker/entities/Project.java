package issueTracker.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "projects")
public class Project {

    private long id;
    private String name;
    private User teamLeader;
    private Set<Issue> issues;
    private List<User> assigneeUsers;
    private List<State> states;

    public Project() {
    }

    public Project(String name) {
        this.setName(name);
        this.issues = new HashSet<>();
        this.assigneeUsers = new ArrayList<>();
        this.states = new ArrayList<>();
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_projects",
            joinColumns = {@JoinColumn(name = "project_id", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "ID")})
    public List<User> getAssigneeUsers() {
        return assigneeUsers;
    }

    public void setAssigneeUsers(List<User> assigneeUsers) {
        this.assigneeUsers = assigneeUsers;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "project")
    public Set<Issue> getIssues() {
        return issues;
    }

    public void setIssues(Set<Issue> issues) {
        this.issues = issues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    public User getTeamLeader() {
        return teamLeader;
    }

    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }
}
