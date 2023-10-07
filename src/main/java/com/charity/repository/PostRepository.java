/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.repository;

import com.charity.pojo.Category;
import com.charity.pojo.Post;
import com.charity.pojo.Product;
import com.charity.pojo.User;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface PostRepository {

    public List<Post> getPosts();

    public Post getPostById(int id);
    
    public boolean addOrUpdatePost(Post p);
    
    public List<Post> getPostsByIdUser(int id);
    
    public boolean deletePostById(int id);
    
    public List<Object[]> countCmtLike();
}
