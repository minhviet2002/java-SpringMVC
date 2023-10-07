/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.controllers;

import com.charity.pojo.Category;
import com.charity.pojo.Product;
import com.charity.service.CategoryService;
import com.charity.service.ProductService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
@RequestMapping(value = "/admin")
public class CategoryController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String index(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories());
        return "indexCategories";
    }

    @GetMapping(value = "/categories/add")
    public String list(Model model) {
        model.addAttribute("categories", new Category());
        return "categories";
    }

    @GetMapping("/categories/update/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("categories", this.categoryService.getCategoryById(id));
        return "categories";
    }

    @PostMapping(value = "/categories/add")
    public String add(Model model, @ModelAttribute(value = "categories") @Valid Category c, BindingResult rs) {
        if (!rs.hasErrors()) {
            if (this.categoryService.addOrUpdateCategory(c) == true) {
                return "redirect:/admin/categories";
            }
        }
        return "categories";
    }

    @PostMapping(value = "/categories/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") int id) {
        if (this.categoryService.deleteCateById(id) == true) {
            return "redirect:/admin/categories";
        }
        return "categories";
    }
}
