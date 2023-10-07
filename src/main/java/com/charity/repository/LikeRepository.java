/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.repository;

import com.charity.pojo.CommentPost;
import com.charity.pojo.LikePost;
import java.util.List;

/**
 *
 * @author ASUS
 */
public interface LikeRepository {
    public List<LikePost> getLikes();
}
