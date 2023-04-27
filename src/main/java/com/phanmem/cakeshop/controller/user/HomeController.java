package com.phanmem.cakeshop.controller.user;


import com.phanmem.cakeshop.entity.Cart;
import com.phanmem.cakeshop.entity.Customer;
import com.phanmem.cakeshop.service.CategoryService;
import com.phanmem.cakeshop.service.CustomerService;
import com.phanmem.cakeshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;

    @GetMapping("/shop")
    public String home(Model model, Principal principal, HttpSession session){
        if(principal != null){
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.getByUsername(principal.getName());
            Cart cart = customer.getCart();
            if(cart !=null){
            session.setAttribute("totalItems", cart.getTotalItems());
            }
        }else{
            session.removeAttribute("username");
        }
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "user/home";
    }
    @GetMapping("home")
    public String getAllProduct(ModelMap model){
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories",categoryService.findAll());
        return "user/home";
    }
    @GetMapping("shop/category/{id}")
    public String getAllProduct(ModelMap model, @PathVariable("id")Long id){
        model.addAttribute("products", productService.findProductByCategoryId(id));
        model.addAttribute("categories",categoryService.findAll());
        return "user/home";
    }

    @GetMapping("low-price")
    public String sortByLowPrice(ModelMap model){
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("products",productService.sortByLowPrice());
        return "user/home";
    }
    @GetMapping("high-price")
    public String sortByHighPrice(Model model){
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("products",productService.sortByHighPrice());
        return "user/home";
    }

}
