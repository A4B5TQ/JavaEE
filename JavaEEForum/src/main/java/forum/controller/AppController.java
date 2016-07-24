package forum.controller;

import forum.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AppController {

    @Autowired
    private UserService userServiceInterface;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String enter(HttpServletRequest request) throws Exception {
        return "redirect:/login";
    }
}
