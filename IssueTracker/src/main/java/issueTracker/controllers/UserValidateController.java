package issueTracker.controllers;

import com.google.gson.Gson;
import issueTracker.entities.User;
import issueTracker.services.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserValidateController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/usernameCheck", method = RequestMethod.POST)
    public
    @ResponseBody
    String usernameCheck(@RequestParam(value = "username") String username){
        User user = this.userService.findOneByName(username);
        if (user != null){
            return new Gson().toJson(true);
        } else {
            return new Gson().toJson(false);
        }
    }
}
