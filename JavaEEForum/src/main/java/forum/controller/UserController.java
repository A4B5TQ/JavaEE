package forum.controller;

import forum.dtos.UserAddForm;
import forum.services.userDetails.UserDetailServiceImpl;
import forum.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/users/userLogin";
    }


    /*@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginProcess(@ModelAttribute UserAddForm userLoginDto){
        String debug = "";
        return "redirect:/categories/all";
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String userRegister(Model model) {
        model.addAttribute("userAddForm", new UserAddForm());
        return "users/UserRegister";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerProcess(@ModelAttribute UserAddForm userLoginForm) {
        this.userService.create(userLoginForm);
        return "redirect:/categories/all";
    }
}
