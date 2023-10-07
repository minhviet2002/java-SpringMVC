/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.controllers;

import com.charity.pojo.Auction;
import com.charity.pojo.Product;
import com.charity.pojo.User;
import com.charity.service.AuctionService;
import com.charity.service.CategoryService;
import com.charity.service.ProductService;
import com.charity.service.ReasonReportService;
import com.charity.service.UserService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Date;

/**
 *
 * @author ASUS
 */
@Controller
public class ProductController {
    
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ReasonReportService reasonReportService;
    
    
    @GetMapping(value = "/products/add")
    public String list(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", this.categoryService.getCategories());
        return "products";
    }
    
    @GetMapping("/products/update/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("categories", this.categoryService.getCategories());
        return "products";
    }
    
    @GetMapping("/products/{id}")
    public String product(Model model, @PathVariable(value = "id") int id, Authentication authentication) {
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("categories", this.categoryService.getCategories());
        model.addAttribute("countAuction", this.auctionService.countAuctionByIdProduct(id));
        model.addAttribute("countReport", this.reasonReportService.countReportByIdProduct(id));
        model.addAttribute("maxPriceAuction", this.auctionService.getAuctionPriceMaxByIdProduct(id));
        model.addAttribute("auction", new Auction());
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            User user = this.userService.getUserByUsername(userName);
            model.addAttribute("listAuction", this.auctionService.getAuctionsByIdUserProd(id, user.getIduser()));
        }
        return "product_detail";
    }
    
    @PostMapping(value = "/products/add")
    public String add(Model model, @ModelAttribute(value = "product") @Valid Product p, BindingResult rs, Authentication authentication) throws ParseException {
        if (!rs.hasErrors()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            User user = this.userService.getUserByUsername(userName);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(p.getDate());
            p.setEndDate(parsedDate);
            if (this.productService.addOrUpdateProduct(p, user) == true) {
                return "redirect:/me";
            }
            
        }
        model.addAttribute("categories", this.categoryService.getCategories());
        return "products";
    }
    
    @PostMapping(value = "/products/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") int id) {
        if (this.productService.deleteProductById(id) == true) {
            return "redirect:/me";
        }
        model.addAttribute("categories", this.categoryService.getCategories());
        return "products";
    }
}
