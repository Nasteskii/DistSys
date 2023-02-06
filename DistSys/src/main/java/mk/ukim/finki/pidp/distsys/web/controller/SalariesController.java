package mk.ukim.finki.pidp.distsys.web.controller;

import mk.ukim.finki.pidp.distsys.model.Employee;
import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.service.EmployeeService;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/salaries")
public class SalariesController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private String message;

    public SalariesController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getSalaries(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        User user = userService.loadUserByUsername(request.getRemoteUser());
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("message", message);
        model.addAttribute("allEmployees", employees);
        model.addAttribute("bodyContent", "salaries");
        message = null;
        return "master-page";
    }
}
