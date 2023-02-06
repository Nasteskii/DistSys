package mk.ukim.finki.pidp.distsys.web.controller;

import javax.servlet.http.HttpServletRequest;

import mk.ukim.finki.pidp.distsys.model.Employee;
import mk.ukim.finki.pidp.distsys.model.Product;
import mk.ukim.finki.pidp.distsys.model.Role;
import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.service.EmployeeService;
import mk.ukim.finki.pidp.distsys.service.ProductService;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private final ProductService productService;
    private final PasswordEncoder encoder;


    public HomeController(UserService userService, EmployeeService employeeService, ProductService productService, PasswordEncoder encoder) {
        this.userService = userService;
        this.employeeService = employeeService;
        this.productService = productService;
        this.encoder = encoder;
    }

    @GetMapping
    public String homePage(Model model, HttpServletRequest request) {
        User user = userService.loadUserByUsername(request.getRemoteUser());

//        WARNING
//        for (int i = 101; i <= 1000; i++) {
//            employeeService.save(new Employee(123456789L+i, "Employee"+i, "Employee"+i, userService.loadUserByUsername("user"+i), LocalDate.now(),
//                    "No", "Male",0, Math.random()*i+30000));
//            String password = encoder.encode("user"+i);
//            userService.register("user"+i, password, password,
//                    "User"+i, "User"+i, "Male", 0, Role.ROLE_USER);
//        }
//        userService.deleteAll();
//        WARNING

        model.addAttribute("user", user);
        model.addAttribute("bodyContent", "home");
        return "master-page";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "master-page";
    }
}
