/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.controllers;

import com.charity.pojo.User;
import com.charity.repository.PostRepository;
import com.charity.service.AuctionService;
import com.charity.service.CategoryService;
import com.charity.service.ProductService;
import com.charity.service.ReasonReportService;
import com.charity.service.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author ASUS
 */
@Controller
public class StatisticController {

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
    @Autowired
    private PostRepository postRepository;

    @GetMapping("/admin/statistics")
    public String index(Model model, Authentication authentication, @RequestParam Map<String, String> params) {
        model.addAttribute("catecount", this.categoryService.countProductCate());
        model.addAttribute("posts", this.postRepository.countCmtLike());
        return "statistics";
    }
}
