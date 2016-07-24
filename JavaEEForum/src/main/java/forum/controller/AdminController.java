package forum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(){
        return "admins/AdminLogin";
    }

    @RequestMapping(value = "/admin/panel", method = RequestMethod.GET)
    public String adminPanel(){
        return "admins/AdminPanel";
    }

    @RequestMapping(value = "/admin/all/users", method = RequestMethod.GET)
    public String test(){
        return "admins/TEST";
    }
}
