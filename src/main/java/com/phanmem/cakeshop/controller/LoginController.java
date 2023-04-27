//package com.phanmem.cakeshop.controller;
//
//import com.phanmem.cakeshop.dto.AdminDto;
//import com.phanmem.cakeshop.dto.CategoryDto;
//import com.phanmem.cakeshop.entity.Admin;
//import com.phanmem.cakeshop.service.AdminService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.validation.Valid;
//
//
//@Controller
//public class LoginController {
//    @Autowired
//    AdminService adminService;
//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//
//    @GetMapping("/login")
//    public String login(Model model){
//        model.addAttribute("user", new AdminDto());
//        return "admin/login";
//    }
//
//    @PostMapping("/login")
//    public String login(@Valid @ModelAttribute("user")AdminDto dto,
//                              BindingResult result,
//                              Model model) {
//        if (result.hasErrors()) {
//            model.addAttribute("user", dto);
//            return "admin/login";
//        }
//        Admin admin = adminService.login(dto.getUserName(), dto.getPassWord());
//        if (admin == null) {
//            model.addAttribute("loginError", "Username or PassWord incorrect!");
//            return "admin/login";
//        }
//        return "redirect:/admin/home";
//    }
//    @GetMapping("/register")
//    public String register(Model model){
//        model.addAttribute("user", new AdminDto());
//        return "admin/register";
//    }
//    @PostMapping("/register-add")
//    public String register(@Valid @ModelAttribute("user")AdminDto dto,
//                           BindingResult result,
//                           Model model){
//
//            if(result.hasErrors()){
//                model.addAttribute("user",dto);
//                result.toString();
//                return "admin/register";
//            }
//                String username = dto.getUserName();
//                Admin admin = adminService.getAdminByUsername(username);
//                if (admin != null) {
//                    model.addAttribute("user", dto);
//                    model.addAttribute("usernameError", "Username has been registered!");
//                    return "admin/register";
//                }
//                Admin entity = new Admin();
//                entity.setUserName(dto.getUserName());
//                entity.setEmail(dto.getEmail());
//
//                if (dto.getPassWord().equals(dto.getRepeatPassword())) {
//                    entity.setPassWord(bCryptPasswordEncoder.encode(dto.getPassWord()));
//                    adminService.save(entity);
//                    model.addAttribute("user", dto);
//                } else {
//                    model.addAttribute("user", dto);
//                    model.addAttribute("passwordError", "Your password maybe wrong! Check again!");
//
//                    return "admin/register";
//                }
//
//
//                return "redirect:/admin/home";
//
//    }
//
//}
