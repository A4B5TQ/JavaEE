package issueTracker.services.projectService;

import issueTracker.auth.CurrentUser;
import issueTracker.dtos.ProjectAddForm;
import issueTracker.entities.Project;
import issueTracker.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public void createProject(ProjectAddForm projectAddForm) {
        String name = projectAddForm.getName();
        Project project = new Project(name);
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        project.getAssigneeUsers().add(currentUser.getUser());
        project.setStates(projectAddForm.getStates());
        this.projectRepository.saveAndFlush(project);
     }

    @Override
    public List<Project> findAllById(Long id) {
        return this.projectRepository.findAllById(id);
    }

    @Override
    public Project getOneById(Long id) {
        return this.projectRepository.findOne(id);
    }

    @Override
    public List<Project> getAll() {
        return this.projectRepository.findAll();
    }
}
