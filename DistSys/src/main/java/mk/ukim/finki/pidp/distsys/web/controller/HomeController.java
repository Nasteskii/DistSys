package mk.ukim.finki.pidp.distsys.web.controller;

import javax.servlet.http.HttpServletRequest;
import mk.ukim.finki.pidp.distsys.service.ClientService;
import mk.ukim.finki.pidp.distsys.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    private final ProductService productService;
    private final ClientService clientService;


    public HomeController(ProductService productService, ClientService clientService) {
        this.productService = productService;
        this.clientService = clientService;
    }

    @GetMapping
    public String homePage(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
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
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("employees", productService.findAll());
        return "products";
    }

    @GetMapping(value = "/clients")
    public String getClients(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("clients", productService.findAll());
        return "products";
    }

    @GetMapping(value = "/info")
    public String getInfo(Model model, HttpServletRequest request) {
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("username", clientService.clientUsername(request.getRemoteUser()));
        model.addAttribute("gender", clientService.clientGender(request.getRemoteUser()));
        model.addAttribute("age", clientService.clientAge(request.getRemoteUser()));
        model.addAttribute("bodyContent", "info");
        return "master-page";
    }
}
