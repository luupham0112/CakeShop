package com.phanmem.cakeshop.controller.user;

import com.phanmem.cakeshop.entity.Cart;
import com.phanmem.cakeshop.entity.Customer;
import com.phanmem.cakeshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class OrderController {
    @Autowired
    CustomerService customerService;
    @PostMapping(value = "/update-infor")
    public String updateCustomer(
            @ModelAttribute("customer") Customer customer,
            Model model,
            RedirectAttributes redirectAttributes,
            Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        Customer customerSaved = customerService.save(customer);

        redirectAttributes.addFlashAttribute("customer", customerSaved);

        return "redirect:/account";
    }
    @GetMapping("/check-out")
    public String checkout(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.getByUsername(username);

            model.addAttribute("customer", customer);
            Cart cart = customer.getCart();
            model.addAttribute("cart", cart);
            model.addAttribute("subTotal", cart.getTotalPrices());

        return "user/order";
    }


}
