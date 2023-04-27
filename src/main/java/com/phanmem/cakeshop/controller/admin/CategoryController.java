package com.phanmem.cakeshop.controller.admin;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.phanmem.cakeshop.dto.CategoryDto;
import com.phanmem.cakeshop.entity.Category;
import com.phanmem.cakeshop.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public String getAllCategory(ModelMap modelMap){
        List<Category> categoryList= categoryService.findAll();
        modelMap.addAttribute("categories",categoryList);
        return "admin/category";

    }
    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "admin/categoryadd";
    }
    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") CategoryDto dto){
        Category entity= new Category();
        BeanUtils.copyProperties(dto,entity);
        categoryService.save(entity);
        return "redirect:/admin/category";

    }
    @GetMapping("/delete/{id}")

    public String deleteCategory(ModelMap modelMap, @PathVariable("id") Long id){
        categoryService.deleteById(id);
        return "redirect:/admin/category";
    }
    @GetMapping("/edit/{id}")
    public String editCategory(Model model, @PathVariable("id") Long id){
        Optional<Category> category= categoryService.findById(id);
        if(category.isPresent()){
            model.addAttribute("category", category);
            return "admin/categoryadd";
        }
        else{
            return "404";
        }
    }
}
