package issueTracker.controllers;

import issueTracker.auth.CurrentUser;
import issueTracker.entities.Authority;
import issueTracker.entities.Project;
import issueTracker.entities.User;
import issueTracker.enums.Role;
import issueTracker.services.projectService.ProjectService;
import issueTracker.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
public class UserController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/issueTracker/welcome",method = RequestMethod.GET)
    public String issueTracker(Model model){
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = this.userService.findOneById(currentUser.getId());
        List<Project> allProjects = user.getProjects();
        model.addAttribute("projects",allProjects);
        return user.getAuthorities()
                .stream()
                .map(Authority::getName)
                .collect(Collectors.toList())
                .contains(Role.ADMIN.toString()) ?
                "admin/MainAdmin":"MainScreen";
    }
}
