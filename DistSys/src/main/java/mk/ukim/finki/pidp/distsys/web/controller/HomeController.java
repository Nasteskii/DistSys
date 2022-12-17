package mk.ukim.finki.pidp.distsys.web.controller;

import javax.servlet.http.HttpServletRequest;

import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.service.ProductService;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    private final ProductService productService;
    private final UserService userService;


    public HomeController(ProductService productService, UserService userService) {
        this.productService = productService;
        this.userService = userService;
    }

    @GetMapping
    public String homePage(Model model, HttpServletRequest request) {
        User user = userService.loadUserByUsername(request.getRemoteUser());
        model.addAttribute("user", user);
        model.addAttribute("bodyContent", "home");
        return "master-page";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "master-page";
    }

    @GetMapping(value = "/employees")
    public String getEmployees(Model model, HttpServletRequest request) {
        User user = userService.loadUserByUsername(request.getRemoteUser());
        model.addAttribute("user", user);
        model.addAttribute("employees", productService.findAll());
        model.addAttribute("bodyContent", "employees");
        return "master-page";
    }

    @GetMapping(value = "/users")
    public String getUsers(Model model, HttpServletRequest request) {
        User user = userService.loadUserByUsername(request.getRemoteUser());
        model.addAttribute("user", user);
        model.addAttribute("users", productService.findAll());
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
