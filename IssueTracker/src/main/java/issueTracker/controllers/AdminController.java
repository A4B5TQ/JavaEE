package issueTracker.controllers;

import issueTracker.services.projectService.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    @Autowired
    ProjectService projectService;

    @RequestMapping(value = "/admin/issueTracker/welcome", method = RequestMethod.GET)
    public String adminLogin(Model model) {
        model.addAttribute("projects", this.projectService.getAll());
        return "admin/MainAdmin";
    }

    @RequestMapping(value = "/issueTracker/allProjects", method = RequestMethod.GET)
    public String allProjects(Model model) {
        model.addAttribute("projects", this.projectService.getAll());
        return "admin/MainAdmin";
    }
}
