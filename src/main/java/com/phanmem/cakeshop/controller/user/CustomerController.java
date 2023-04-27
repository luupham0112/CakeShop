package com.phanmem.cakeshop.controller.user;

import com.phanmem.cakeshop.dto.AdminDto;
import com.phanmem.cakeshop.dto.CategoryDto;
import com.phanmem.cakeshop.dto.CustomerDto;
import com.phanmem.cakeshop.entity.Admin;
import com.phanmem.cakeshop.entity.Customer;
import com.phanmem.cakeshop.service.AdminService;
import com.phanmem.cakeshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;


    @GetMapping("/login")
    public String login(Model model){

        return "user/login";
    }
//
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute("user")CustomerDto dto,
//                        BindingResult result,
//                        Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("user", dto);
//            return "user/login";
//        }
//       Customer customer = customerService.login(dto.getUserName(), dto.getPassWord());
//        if (customer == null) {
//            model.addAttribute("loginError", "Username or PassWord incorrect!");
//            return "user/login";
//        }
//        return "redirect:/shop";
//    }
    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user", new CustomerDto());
        return "user/register";
    }
    @PostMapping("/register-add")
    public String register(@Valid @ModelAttribute("user")CustomerDto dto,
                           BindingResult result,
                           Model model){

        if(result.hasErrors()){
            model.addAttribute("user",dto);
            result.toString();
            return "user/register";
        }
        String username = dto.getUserName();
        Customer customer= customerService.getByUsername(username);
        if (customer != null) {
            model.addAttribute("user", dto);
            model.addAttribute("usernameError", "Username has been registered!");
            return "user/register";
        }
        Customer entity = new Customer();
        entity.setUsername(dto.getUserName());

        if (dto.getPassWord().equals(dto.getRepeatPassword())) {
            entity.setPassword(cryptPasswordEncoder.encode(dto.getPassWord()));
           customerService.save(entity);
           model.addAttribute("success","Register successfully");
            model.addAttribute("user", dto);
        } else {
            model.addAttribute("user", dto);
            model.addAttribute("passwordError", "Your password maybe wrong! Check again!");

            return "user/register";
        }
        return "user/register";

    }

}
