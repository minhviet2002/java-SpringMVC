package com.charity.controllers;

import com.charity.service.AuctionService;
import com.charity.service.CategoryService;
import com.charity.service.ProductService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ASUS
 */
@Controller
public class HomeController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AuctionService auctionService;

    @GetMapping("/")
    public String index(Model model, Authentication authentication, @RequestParam Map<String, String> params) {
        model.addAttribute("categories", this.categoryService.getCategories());
        model.addAttribute("products", this.productService.getProducts(params));
        int pageSize = Integer.parseInt("3");
        int count = this.productService.countProduct();
        model.addAttribute("counter", Math.ceil(count * 1.0 / pageSize));
        model.addAttribute("listAuction", this.auctionService.getAuctions());
        return "index";
    }

    @GetMapping("/productsbycategory/{id}")
    public String productsByCate(Model model, @PathVariable(value = "id") int id, @RequestParam Map<String, String> params) {
        model.addAttribute("categories", this.categoryService.getCategories());
        model.addAttribute("products", this.productService.getProductsByIdCate(id, params));
        int pageSize = Integer.parseInt("3");
        int count = this.productService.countProductByIdCate(id);
        model.addAttribute("counterCate", Math.ceil(count * 1.0 / pageSize));
        return "index";
    }
;
}
