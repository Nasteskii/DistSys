package mk.ukim.finki.pidp.distsys.web.controller;

import javax.servlet.http.HttpServletRequest;
import mk.ukim.finki.pidp.distsys.model.Product;
import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.service.ProductService;
import mk.ukim.finki.pidp.distsys.service.ShoppingCartService;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private String message;

    public ProductController(ProductService productService, UserService userService, ShoppingCartService shoppingCartService) {
        this.productService = productService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = this.productService.findAll();
        User user = userService.loadUserByUsername(request.getRemoteUser());
        model.addAttribute("user", user);
        model.addAttribute("message", message);
        model.addAttribute("allProducts", products);
        model.addAttribute("bodyContent", "products");
        message = null;
        return "master-page";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        if (this.productService.findById(id) != null) {
            message = this.productService.deleteById(id);
            return "redirect:/products";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-page";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddForm(Model model) {
        model.addAttribute("bodyContent", "add-product");
        return "master-page";
    }

    @PostMapping("/add-product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price) {
        if (id != null) {
            message = this.productService.edit(id, new Product(name, description, price));
        } else {
            if (this.productService.findByName(name).isPresent()) {
                return "redirect:/products?error=Product Already Exists";
            }
            else {
                message = this.productService.save(new Product(name, description, price));
            }
        }
        return "redirect:/products";
    }

    @PostMapping("/add-to-cart/{id}")
    public String addProductToCart(@PathVariable("id") long id){
        Product product = productService.findById(id).get();
        shoppingCartService.addProduct(product);
        message = "Product " + product.getName() + " was added to your shopping cart!";
        return "redirect:/products";
    }
}
