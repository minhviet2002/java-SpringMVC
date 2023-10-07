/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.controllers;

import com.charity.pojo.CommentPost;
import com.charity.pojo.Post;
import com.charity.pojo.Product;
import com.charity.pojo.ReasonReport;
import com.charity.pojo.User;
import com.charity.service.AuctionService;
import com.charity.service.CategoryService;
import com.charity.service.CommentService;
import com.charity.service.LikeService;
import com.charity.service.PostService;
import com.charity.service.ProductService;
import com.charity.service.ReasonReportService;
import com.charity.service.ReasonService;
import com.charity.service.UserService;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
public class PostController {

    @Autowired
    private ProductService productService;
    @Autowired
    private PostService postService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuctionService auctionService;
    @Autowired
    private ReasonReportService reasonReportService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private LikeService likeService;
    @Autowired
    private ReasonService reasonService;

    @GetMapping(value = "/post/add")
    public String postCreated(Model model) {
        model.addAttribute("post", new Post());
        return "post";
    }

    @GetMapping("/posts/update/{id}")
    public String update(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("post", this.postService.getPostById(id));
        return "post";
    }

    @GetMapping("/posts")
    public String index(Model model, Authentication authentication) {
        model.addAttribute("post", new Post());

        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            User user = this.userService.getUserByUsername(userName);
            model.addAttribute("user", user);
        }
        model.addAttribute("reasons", this.reasonService.getReasons());
        model.addAttribute("report", new ReasonReport());
        model.addAttribute("posts", this.postService.getPosts());
        model.addAttribute("comment", new CommentPost());
        model.addAttribute("comments", this.commentService.getComments());
        model.addAttribute("likes", this.likeService.getLikes());
        return "posts";
    }

    @PostMapping("/comment/add/{id}")
    public String comment(Model model, Authentication authentication, @PathVariable(value = "id") int id, @ModelAttribute(value = "comment") CommentPost c) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            User user = this.userService.getUserByUsername(userName);
            c.setIduser(user);
            Post p = this.postService.getPostById(id);
            c.setIdpost(p);
            c.setCreatedDate(new Date());
            if (this.commentService.addComment(c) == true) {
                return "redirect:/posts";
            }
        }
        return "posts";
    }

    @PostMapping("/me/comment/add/{id}")
    public String commentMe(Model model, Authentication authentication, @PathVariable(value = "id") int id, @ModelAttribute(value = "comment") CommentPost c) {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            User user = this.userService.getUserByUsername(userName);
            c.setIduser(user);
            Post p = this.postService.getPostById(id);
            c.setIdpost(p);
            c.setCreatedDate(new Date());
            if (this.commentService.addComment(c) == true) {
                return "redirect:/me";
            }
        }
        return "posts";
    }

    @PostMapping("/post/add")
    public String post(Model model, Authentication authentication, @ModelAttribute(value = "post") @Valid Post p, BindingResult rs) {
        if (!rs.hasErrors()) {
            if (authentication != null) {
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                String userName = userDetails.getUsername();
                User user = this.userService.getUserByUsername(userName);
                p.setIduser(user);
                if (this.postService.addOrUpdatePost(p) == true) {
                    return "redirect:/posts";
                }
            }
        } 
        return "posts";
    }

    @PostMapping("/comment/delete/{id}")
    public String deleteComment(Model model, @PathVariable(value = "id") int id) {
        if (this.commentService.deleteCommentById(id) == true) {
            return "redirect:/posts";
        }
        return "posts";
    }

    @PostMapping("/me/comment/delete/{id}")
    public String deleteCommentMe(Model model, @PathVariable(value = "id") int id) {
        if (this.commentService.deleteCommentById(id) == true) {
            return "redirect:/me";
        }
        return "posts";
    }

    @PostMapping(value = "/posts/delete/{id}")
    public String delete(Model model, @PathVariable(value = "id") int id) {
        if (this.postService.deletePostById(id) == true) {
            return "redirect:/posts";
        }
        return "posts";
    }

    @PostMapping(value = "/me/posts/delete/{id}")
    public String deleteMe(Model model, @PathVariable(value = "id") int id) {
        if (this.postService.deletePostById(id) == true) {
            return "redirect:/posts";
        }
        return "posts";
    }

}
