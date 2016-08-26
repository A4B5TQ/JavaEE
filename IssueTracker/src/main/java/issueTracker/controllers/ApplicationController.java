package issueTracker.controllers;

import issueTracker.dtos.UserAddForm;
import issueTracker.enums.Role;
import issueTracker.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
public class ApplicationController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginPage(){
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String userLogin() {
        return "Login";
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String userRegister(Model model) {
        model.addAttribute("roles", Arrays.stream(Role.values())
                .map(Enum::toString)
                .collect(Collectors.toList()));
        model.addAttribute("userAddForm", new UserAddForm());
        return "admin/AddNewUser";
    }

    //@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerProcess(@ModelAttribute UserAddForm userAddForm) {
        this.userService.createUser(userAddForm);
        return "redirect:/admin/issueTracker/welcome";
    }
}
