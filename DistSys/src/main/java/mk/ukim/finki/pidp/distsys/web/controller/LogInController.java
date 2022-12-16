package mk.ukim.finki.pidp.distsys.web.controller;//package mk.ukim.finki.pidp.distsys.web.controller;
//
//import jakarta.servlet.http.HttpServletRequest;
//import mk.ukim.finki.pidp.distsys.model.Client;
//import mk.ukim.finki.pidp.distsys.model.exceptions.InvalidUserCredentialsException;
//import mk.ukim.finki.pidp.distsys.service.AuthService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping(value = "/login")
//public class LogInController {
//    private final AuthService authService;
//
//    public LogInController(AuthService authService) {
//        this.authService = authService;
//    }
//
//    @GetMapping
//    public String getLoginPage(Model model) {
//        model.addAttribute("bodyContent", "login");
//        return "master-page";
//    }
//
//    @PostMapping
//    public String login(HttpServletRequest request, Model model) {
//        Client client = null;
//        try{
//            client = this.authService.login(request.getParameter("username"),
//                    request.getParameter("password"));
//            request.getSession().setAttribute("user", client);
//            return "redirect:/home";
//        }
//        catch (InvalidUserCredentialsException exception) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", exception.getMessage());
//            return "login";
//        }
//    }
//
//}
