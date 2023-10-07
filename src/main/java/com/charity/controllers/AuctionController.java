/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.controllers;

import com.charity.pojo.Auction;
import com.charity.pojo.Product;
import com.charity.pojo.User;
import com.charity.service.AuctionService;
import com.charity.service.ProductService;
import com.charity.service.UserService;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ASUS
 *
 */
@Controller
public class AuctionController {

    @Autowired
    private AuctionService auctionService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @PostMapping(value = "auction/add/{id}")
    public String add(@PathVariable(value = "id") int id, RedirectAttributes attributes, Model model, @ModelAttribute(value = "auction") @Valid Auction a, BindingResult rs, Authentication authentication) {
        if (!rs.hasErrors()) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String userName = userDetails.getUsername();
            User u = this.userService.getUserByUsername(userName);
            a.setIduser(u);
            Product p = this.productService.getProductById(id);
            a.setIdproduct(p);
            a.setActive(Boolean.FALSE);
            a.setCreatedDate(new Date());
            if (this.auctionService.addOrUpdateAuction(a) == true) {
                attributes.addAttribute("id", id);
                return "redirect:/products/{id}";
            }
        }
        return "product_detail";
    }

    @PostMapping(value = "auctionActive/{id}")
    public String auctionActive(@PathVariable(value = "id") int id, Model model, RedirectAttributes attributes) {
        Auction a = this.auctionService.getAuctionById(id);
        a.setActive(Boolean.TRUE);
        Product p = this.productService.getProductById(a.getIdproduct().getIdproduct());
        p.setActive(Boolean.FALSE);
        p.setEndprice(a.getPrice());
        if (this.productService.updateProductActive(p) == true && this.auctionService.addOrUpdateAuction(a) == true) {
            attributes.addAttribute("id", a.getIdproduct().getIdproduct());
//            sendMail(a.getIdproduct().getUserCreated().getEmail(), a.getIduser().getEmail(), a.getIdproduct().getName(), "Chúc mừng bạn đã đấu giá thành công sản phẩm. Vui lòng thanh toán đúng hạn");
            return "redirect:/me/product/{id}";
        }
        return null;
    }

//    public void sendMail(String from, String to, String subject, String content) {
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setFrom(from);
//        mailMessage.setTo(to);
//        mailMessage.setSubject(subject);
//        mailMessage.setText(content);
//        mailSender.send(mailMessage);
//    }

}
