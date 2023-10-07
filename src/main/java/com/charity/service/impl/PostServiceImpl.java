/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.service.impl;

import com.charity.pojo.Post;
import com.charity.repository.CategoryRepository;
import com.charity.repository.PostRepository;
import com.charity.service.PostService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service

public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Post> getPosts() {
        return this.postRepository.getPosts();
    }

    @Override
    public Post getPostById(int id) {
        return this.postRepository.getPostById(id);
    }

    @Override
    public boolean addOrUpdatePost(Post p) {
        if (!p.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(p.getFile().getBytes(), ObjectUtils.asMap("resource_type", "auto"));
                p.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(PostServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return this.postRepository.addOrUpdatePost(p);
    }

    @Override
    public List<Post> getPostsByIdUser(int id) {
       return this.postRepository.getPostsByIdUser(id);
    }

    @Override
    public boolean deletePostById(int id) {
       return this.postRepository.deletePostById(id);
    }

    @Override
    public List<Object[]> countCmtLike() {
        return this.postRepository.countCmtLike();
    }

}
