package issueTracker.controllers;

import issueTracker.dtos.StateAddForm;
import issueTracker.entities.Project;
import issueTracker.enums.Visibility;
import issueTracker.services.projectService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class StateController {

    @Autowired
    private ProjectService projectService;

    @RequestMapping(value = "/createState", method = RequestMethod.GET)
    public String createState(Model model){
        model.addAttribute("stateAddForm", new StateAddForm());
        model.addAttribute("visibilities", Arrays.stream(Visibility.values()).collect(Collectors.toList()));
        model.addAttribute("projects",this.projectService.getAll().stream()
        .map(Project::getName).toArray(String[]::new));
        return "admin/AddNewState";
    }

    @RequestMapping(value = "/createState", method = RequestMethod.POST)
    public String createState(@ModelAttribute StateAddForm stateAddForm){
        String debug = "";
        return "redirect:/issueTracker/welcome";
    }
}
