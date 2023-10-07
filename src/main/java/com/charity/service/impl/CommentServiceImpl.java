/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.service.impl;

import com.charity.pojo.CommentPost;
import com.charity.repository.AuctionRepository;
import com.charity.repository.CommentRepository;
import com.charity.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public boolean addComment(CommentPost c) {
        return this.commentRepository.addComment(c);
    }

    @Override
    public List<CommentPost> getComments() {
        return this.commentRepository.getComments();
    }

    @Override
    public boolean deleteCommentById(int id) {
        return this.commentRepository.deleteCommentById(id);
    }

    @Override
    public CommentPost getCommentById(int id) {
        return this.commentRepository.getCommentById(id);
    }

}
