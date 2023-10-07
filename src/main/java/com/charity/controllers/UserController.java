/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.controllers;

import com.charity.pojo.CommentPost;
import com.charity.pojo.ReasonReport;
import com.charity.pojo.User;
import com.charity.service.AuctionService;
import com.charity.service.CommentService;
import com.charity.service.LikeService;
import com.charity.service.PostService;
import com.charity.service.ProductService;
import com.charity.service.ReasonReportService;
import com.charity.service.ReasonService;
import com.charity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ASUS
 */
@Controller
public class UserController {

    @Autowired
    private UserService userDetailsService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReasonService reasonService;
    @Autowired
    private ReasonReportService reasonReportService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;

    @GetMapping("/admin/users")
    public String adminUser(Model model) {
        model.addAttribute("users", this.userDetailsService.getUsers());
        return "users";
    }

    @PostMapping("/admin/users/active/{id}")
    public String activeUser(Model model, @PathVariable(value = "id") int id) {
        User user = this.userDetailsService.getUserById(id);
        user.setActive(Boolean.TRUE);
        if (this.userDetailsService.updateUser(user) == true) {
            model.addAttribute("users", this.userDetailsService.getUsers());
            return "redirect:/admin/users";
        }
        return "users";
    }

    @PostMapping("/admin/users/unactive/{id}")
    public String unActiveUser(Model model, @PathVariable(value = "id") int id) {
        User user = this.userDetailsService.getUserById(id);
        user.setActive(Boolean.FALSE);
        if (this.userDetailsService.updateUser(user) == true) {
            model.addAttribute("users", this.userDetailsService.getUsers());
            return "redirect:/admin/users";
        }
        return "users";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(Model model, @ModelAttribute(value = "user") User user) {
        String errMsg = "";
        if (user.getConfirmPassword().equals(user.getPassword())) {
            if (this.userDetailsService.addUser(user) == true) {
                if (user.getIduser() == null) {
                    return "redirect:/register";
                } else {
                    return "redirect:/me";
                }
            } else {
                errMsg = "Có lỗi!";
            }
        } else {
            errMsg = "Mật khẩu xác nhận không đúng!";

        }
        model.addAttribute("errMsg", errMsg);
        return "register";
    }

    @GetMapping("/me")
    public String me(Model model, Authentication authentication) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            User user = this.userDetailsService.getUserByUsername(userName);
            model.addAttribute("user", user);
            model.addAttribute("listAuction", this.auctionService.getAuctionsByIdUser(user.getIduser()));
            model.addAttribute("products", this.productService.getProductsByIdUser(user.getIduser()));
            model.addAttribute("posts", this.postService.getPostsByIdUser(user.getIduser()));
            model.addAttribute("comment", new CommentPost());
            model.addAttribute("comments", this.commentService.getComments());
            model.addAttribute("likes", this.likeService.getLikes());
        }
        return "user";
    }

    @GetMapping("/me/update")
    public String update(Model model, Authentication authentication) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            User user = this.userDetailsService.getUserByUsername(userName);
            model.addAttribute("user", user);
        }
        return "register";
    }

    @GetMapping("/user/{username}")
    public String update(Model model, @PathVariable(value = "username") String username) {
        if (username != null) {
            User user = this.userDetailsService.getUserByUsername(username);
            model.addAttribute("user", user);
            model.addAttribute("listAuction", this.auctionService.getAuctionsByIdUser(user.getIduser()));
            model.addAttribute("products", this.productService.getProductsByIdUser(user.getIduser()));
            model.addAttribute("reasons", this.reasonService.getReasons());
            model.addAttribute("listReport", this.reasonReportService.getReasonReportByIdUser(user.getIduser()));
            model.addAttribute("report", new ReasonReport());
        }
        return "user";
    }

    @GetMapping("/me/product/{id}")
    public String update(Model model, @PathVariable(value = "id") int id, Authentication authentication) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            User user = this.userDetailsService.getUserByUsername(userName);
            model.addAttribute("user", user);
        }
        model.addAttribute("listAuction", this.auctionService.getAuctionsByIdPro(id));
        model.addAttribute("product", this.productService.getProductById(id));
        model.addAttribute("countAuction", this.auctionService.countAuctionByIdProduct(id));
        model.addAttribute("maxPriceAuction", this.auctionService.getAuctionPriceMaxByIdProduct(id));

        return "productMe";
    }

    @PostMapping("/user/report/{username}")
    public String register(Model model, @ModelAttribute(value = "report") ReasonReport r, @PathVariable(value = "username") String userName, RedirectAttributes attributes, Authentication authentication) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String name = userDetails.getUsername();
            User user = this.userDetailsService.getUserByUsername(name);
            r.setIduser(user);
            User userReported = this.userDetailsService.getUserByUsername(userName);
            r.setIduserreported(userReported);
            if (this.reasonReportService.addReasonReport(r) == true) {
                attributes.addAttribute("username", userName);
                return "redirect:/user/{username}";
            }
        }
        return "user";
    }
}
