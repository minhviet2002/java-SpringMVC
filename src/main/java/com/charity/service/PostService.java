/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.charity.service;

import com.charity.pojo.Post;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface PostService {

    public List<Post> getPosts();

    public Post getPostById(int id);

    public boolean addOrUpdatePost(Post p);

    public List<Post> getPostsByIdUser(int id);

    public boolean deletePostById(int id);

    public List<Object[]> countCmtLike();
}
