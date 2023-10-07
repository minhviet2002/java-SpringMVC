/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.repository;

import com.charity.pojo.Category;
import com.charity.pojo.CommentPost;
import com.charity.pojo.Product;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface CommentRepository {

    public boolean addComment(CommentPost c);

    public List<CommentPost> getComments();

    public CommentPost getCommentById(int id);

    public boolean deleteCommentById(int id);
}
