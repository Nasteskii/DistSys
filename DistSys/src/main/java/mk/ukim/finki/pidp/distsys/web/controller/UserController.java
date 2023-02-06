package mk.ukim.finki.pidp.distsys.web.controller;

import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model, HttpServletRequest request) {
        User user = userService.loadUserByUsername(request.getRemoteUser());
        model.addAttribute("user", user);
        model.addAttribute("allUsers", userService.findAll());
        model.addAttribute("bodyContent", "users");
        return "master-page";
    }

    @GetMapping(value = "/info")
    public String getInfo(Model model, HttpServletRequest request) {
        User user = userService.loadUserByUsername(request.getRemoteUser());
        model.addAttribute("user", user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("name", user.getFirstName());
        model.addAttribute("surname", user.getLastName());
        model.addAttribute("gender", user.getGender());
        model.addAttribute("age", user.getAge());
        model.addAttribute("bodyContent", "info");
        return "master-page";
    }
}
