package mk.ukim.finki.pidp.distsys.web.controller;

import javax.servlet.http.HttpServletRequest;
import mk.ukim.finki.pidp.distsys.model.Product;
import mk.ukim.finki.pidp.distsys.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model, HttpServletRequest request) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = this.productService.findAll();
        model.addAttribute("user", request.getRemoteUser());
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-page";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id) != null) {
            Product product = this.productService.findById(id);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-page";
        }
        return "redirect:/products?error=ProductNotFound";
    }

//    @GetMapping("/add-to-cart")
//    public String addProductPage(Model model) {
//        model.addAttribute("bodyContent", "add-product");
//        return "master-page";
//    }

    @PostMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam Double price) {
        if (id != null) {
            this.productService.edit(id, new Product(name, description, price));
        } else {
            this.productService.save(new Product(name, description, price));
        }
        return "redirect:/products";
    }
}
