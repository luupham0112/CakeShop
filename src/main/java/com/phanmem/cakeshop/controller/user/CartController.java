package com.phanmem.cakeshop.controller.user;

import com.phanmem.cakeshop.entity.Cart;
import com.phanmem.cakeshop.entity.Customer;
import com.phanmem.cakeshop.entity.Product;
import com.phanmem.cakeshop.service.CustomerService;
import com.phanmem.cakeshop.service.ProductService;
import com.phanmem.cakeshop.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    ShoppingCartService cartService;

    @GetMapping("/shopping-cart")
    public String cart(Model model, Principal principal, HttpSession session){
        if(principal==null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.getByUsername(username);
        Cart shoppingCart = customer.getCart();
        if(shoppingCart == null){
            model.addAttribute("check", "No item in your cart");
        }
        else {
            session.setAttribute("totalItems", shoppingCart.getTotalItems());
            model.addAttribute("subTotal", shoppingCart.getTotalPrices());

            model.addAttribute("cart", shoppingCart);
        }
        return "user/cart";
    }
    @GetMapping("/add-to-cart/{id}")
    public String addItemToCart(
            @PathVariable("id") Long productId,
            @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
            Principal principal,
            HttpServletRequest request){

        if(principal == null){
            return "redirect:/login";
        }
        Product product = productService.getById(productId);
        String username = principal.getName();
        Customer customer = customerService.getByUsername(username);

        Cart cart = cartService.addItemToCart(product, quantity, customer);
        return "redirect:/shop" ;

    }

    @PostMapping(value = "/update-cart")
    public String updateCart(@RequestParam("quantity") int quantity,
                             @RequestParam("id") Long productId,
                             Model model,
                             Principal principal
    ){
        if(principal == null){
            return "redirect:/login";
        }else{
            String username = principal.getName();
            Customer customer = customerService.getByUsername(username);
            Product product = productService.getById(productId);
            Cart cart = cartService.updateItemInCart(product, quantity, customer);

            model.addAttribute("shoppingCart", cart);
            return "redirect:/shopping-cart";
        }

    }
    @GetMapping(value = "/delete-cart/{id}")
    public String deleteItemFromCart(@PathVariable("id") Long productId,
                                     Model model,
                                     Principal principal){
        if(principal == null){
            return "redirect:/login";
        }else{
            String username = principal.getName();
            Customer customer = customerService.getByUsername(username);
            Product product = productService.getById(productId);
            Cart cart = cartService.deleteItemFromCart(product, customer);
            model.addAttribute("shoppingCart", cart);
            return "redirect:/shopping-cart";
        }

    }

}
