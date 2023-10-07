/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.service.impl;

import com.charity.pojo.LikePost;
import com.charity.repository.CommentRepository;
import com.charity.repository.LikeRepository;
import com.charity.service.LikeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public List<LikePost> getLikes() {
        return this.likeRepository.getLikes();
    }

}
