/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.repository.impl;

import com.charity.pojo.LikePost;
import com.charity.repository.LikeRepository;
import com.charity.repository.PostRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ASUS
 */
@Repository
@Transactional
public class LikeRepositoryImpl implements LikeRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<LikePost> getLikes() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("From LikePost");
        return q.getResultList();
    }
}
