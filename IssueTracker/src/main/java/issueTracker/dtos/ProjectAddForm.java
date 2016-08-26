package issueTracker.dtos;

import issueTracker.entities.State;

import java.util.ArrayList;
import java.util.List;

public class ProjectAddForm {
    private String name;
    private List<State> states = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<State> getStates() {
        return states;
    }

    public void setStates(List<State> states) {
        this.states = states;
    }
}
