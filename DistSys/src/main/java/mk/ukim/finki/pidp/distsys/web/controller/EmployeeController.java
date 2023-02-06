package mk.ukim.finki.pidp.distsys.web.controller;

import mk.ukim.finki.pidp.distsys.model.Employee;
import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.service.EmployeeService;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final UserService userService;
    private final EmployeeService employeeService;
    private String message;

    public EmployeeController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public String getEmployees(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        User user = userService.loadUserByUsername(request.getRemoteUser());
        List<Employee> employees = employeeService.findAll();
        model.addAttribute("user", user);
        model.addAttribute("message", message);
        model.addAttribute("allEmployees", employees);
        model.addAttribute("bodyContent", "employees");
        message = null;
        return "master-page";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddForm(Model model) {
        model.addAttribute("bodyContent", "add-employee");
        return "master-page";
    }

    @PostMapping("/add-employee")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveEmployee(
            @RequestParam Long ssn,
            @RequestParam String fName,
            @RequestParam String lName,
            @RequestParam String username,
            @RequestParam String dateEmployment,
            @RequestParam String isAdmin,
            @RequestParam String gender,
            @RequestParam Integer age,
            @RequestParam Double grossWage) {
        String pattern = "yyyy-MM-dd";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        if (this.employeeService.findBySsn(ssn).isPresent()) {
            return "redirect:/employees?error=Employee Already Exists!";
        } else {
            User user = null;
            try {
                user = userService.loadUserByUsername(username);
            } catch (Exception e) {
                return "redirect:/employees?error=User Doesn't Exists!";
            }
            message = this.employeeService.save(new Employee(ssn, fName, lName, user, LocalDate.parse(dateEmployment, formatter), isAdmin, gender, age, grossWage));
        }
        return "redirect:/employees";
    }
}
