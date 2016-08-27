package issueTracker.dtos;

public class StateAddForm {
    private String name;
    private String visibility;
    private String project;

    public StateAddForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String getProjects() {
        return this.project;
    }

    public void setProjects(String project) {
        this.project = project;
    }
}
