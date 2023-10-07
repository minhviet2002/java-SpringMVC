/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.charity.repository.impl;

import com.charity.pojo.Category;
import com.charity.pojo.CommentPost;
import com.charity.repository.CommentRepository;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.HibernateException;
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
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public boolean addComment(CommentPost c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(c);
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

    @Override
    public List<CommentPost> getComments() {
        Session s = factory.getObject().getCurrentSession();
        Query q = s.createQuery("From CommentPost");
        return q.getResultList();
    }

    @Override
    public CommentPost getCommentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(CommentPost.class, id);
    }

    @Override
    public boolean deleteCommentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.delete(this.getCommentById(id));
            return true;
        } catch (HibernateException ex) {
            System.err.println(ex.getMessage());
        }
        return false;
    }

}
