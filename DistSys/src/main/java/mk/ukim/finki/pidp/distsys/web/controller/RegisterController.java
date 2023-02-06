package mk.ukim.finki.pidp.distsys.web.controller;

import javax.servlet.http.HttpServletRequest;

import mk.ukim.finki.pidp.distsys.model.Role;
import mk.ukim.finki.pidp.distsys.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.pidp.distsys.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.pidp.distsys.model.exceptions.UsernameAlreadyExistsException;
import mk.ukim.finki.pidp.distsys.service.AuthService;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent", "register");
        return "master-page";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam(required = false) String surname,
                           @RequestParam(required = false) String gender,
                           @RequestParam(required = false) Integer age,
                           @RequestParam Role role, Model model) {
        try {
            this.userService.register(username, password, repeatedPassword,
                    name, surname, gender, age, role);
            return "redirect:/login";
        } catch (InvalidUserCredentialsException | PasswordsDoNotMatchException |
                 UsernameAlreadyExistsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

}
