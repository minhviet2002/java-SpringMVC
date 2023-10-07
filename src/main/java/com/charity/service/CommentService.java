/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.service;

import com.charity.pojo.CommentPost;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface CommentService {

    public boolean addComment(CommentPost c);

    public List<CommentPost> getComments();

    public CommentPost getCommentById(int id);

    public boolean deleteCommentById(int id);
}
