package issueTracker.services.projectService;

import issueTracker.dtos.ProjectAddForm;
import issueTracker.entities.Project;

import java.util.List;

public interface ProjectService {
    void createProject(ProjectAddForm projectAddForm);
    List<Project> findAllById(Long id);
    Project getOneById(Long id);

    List<Project> getAll();
}
