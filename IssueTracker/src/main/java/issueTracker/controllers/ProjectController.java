package issueTracker.controllers;

import issueTracker.dtos.ProjectAddForm;
import issueTracker.entities.Project;
import issueTracker.entities.State;
import issueTracker.services.projectService.ProjectService;
import issueTracker.services.stateService.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private StateService stateService;


    @RequestMapping(value = "issueTracker/createProject", method = RequestMethod.GET)
    public String createProject(Model model) {
        List<State> allStates = this.stateService.getAll();
        model.addAttribute("projectAddForm", new ProjectAddForm());
        model.addAttribute("states", allStates);
        return "CreateProject";
    }

    @RequestMapping(value = "issueTracker/createProject", method = RequestMethod.POST)
    public String processProject(@ModelAttribute ProjectAddForm projectAddForm) {
        this.projectService.createProject(projectAddForm);
        return "redirect:/issueTracker/welcome";
    }

    @RequestMapping(value = "/edit/project/{id}",method = RequestMethod.GET)
    public String selectProject(Model model,
                                @PathVariable(value = "id") Long id){
        Project project = this.projectService.getOneById(id);
        model.addAttribute("project",project);
        model.addAttribute("states", project.getStates());
        return "SelectedProject";
    }
}
