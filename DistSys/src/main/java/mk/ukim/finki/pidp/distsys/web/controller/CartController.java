package mk.ukim.finki.pidp.distsys.web.controller;

import javax.servlet.http.HttpServletRequest;
import mk.ukim.finki.pidp.distsys.model.Product;
import mk.ukim.finki.pidp.distsys.model.User;
import mk.ukim.finki.pidp.distsys.service.ProductService;
import mk.ukim.finki.pidp.distsys.service.ShoppingCartService;
import mk.ukim.finki.pidp.distsys.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shopping-cart")
public class CartController {

    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final ProductService productService;

    private String message;

    public CartController(ShoppingCartService shoppingCartService, UserService userService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping
    public String cart(Model model, HttpServletRequest request){
        User user = userService.loadUserByUsername(request.getRemoteUser());
        model.addAttribute("user", user);
        model.addAttribute("products", shoppingCartService.productsInCart());
        model.addAttribute("totalPrice", shoppingCartService.totalPrice());
        model.addAttribute("message", message);
        model.addAttribute("bodyContent", "cart");
        message = null;
        return "master-page";
    }

    @PostMapping("/remove-product/{id}")
    public String removeProductFromCart(@PathVariable("id") long id, Model model){
        Product product = productService.findById(id).get();
        shoppingCartService.removeProduct(product);
        message = "Product " + product.getName() + " was removed from shopping cart!";
        model.addAttribute("message", String.format("Product with id: %s removed from shopping cart.", id));
        return "redirect:/shopping-cart";
    }

    @GetMapping("/clear")
    public String clearProductsInCart(){
        shoppingCartService.clearProducts();
        message = "Shopping cart was cleared!";
        return "redirect:/shopping-cart";
    }

    @GetMapping("/checkout")
    public String cartCheckout(){
        shoppingCartService.cartCheckout();
        return "redirect:/shopping-cart";
    }
}

